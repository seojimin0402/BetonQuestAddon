package com.github.mrjimin.betonquestaddon.compatibility

import org.betonquest.betonquest.BetonQuest
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

abstract class AbstractObjective<T>(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    langMessageKey: LangMessageKey,
    private val log: BetonQuestLogger,
    protected val itemID: Variable<T>
) : CountingObjective(instruction, targetAmount, langMessageKey.key), Listener {

    protected abstract fun matches(expected: T, inputId: String?): Boolean

    protected fun handle(namespacedID: String?, player: Player?) {
        val profile: OnlineProfile = BetonQuest.getInstance().profileProvider.getProfile(player)
        try {
            val expected = itemID.getValue(profile)
            if (containsPlayer(profile) && matches(expected, namespacedID) && checkConditions(profile)) {
                getCountingData(profile).progress()
                completeIfDoneOrNotify(profile)
            }
        } catch (e: QuestException) {
            log.warn("Could not resolve Item Variable in objective '${instruction.id}': ${e.message}", e)
        }
    }

    override fun start() {
        Bukkit.getPluginManager().registerEvents(this, BetonQuest.getInstance())
    }

    override fun stop() {
        HandlerList.unregisterAll(this)
    }

}