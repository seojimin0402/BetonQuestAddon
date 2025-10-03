package com.github.mrjimin.betonquestaddon.compatibility

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.entity.Player

abstract class AbstractItemObjective<T>(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    langMessageKey: LangMessageKey,
    private val itemID: Variable<T>
) : AbstractBaseObjective(instruction, targetAmount, langMessageKey) {

    protected abstract fun matches(expected: T, inputId: String?): Boolean

    override fun checkMatch(profile: OnlineProfile, input: Any?): Boolean {
        val inputId = input as? String ?: return false
        return try {
            val expected = itemID.getValue(profile)
            matches(expected, inputId)
        } catch (e: QuestException) {
            BetonQuestAddon.logger.warn("Could not resolve Item Variable in objective '${instruction.id}': ${e.message}", e)
            false
        }
    }


    fun handle(namespacedID: String?, player: Player?) =
        handle(player, namespacedID)
}
