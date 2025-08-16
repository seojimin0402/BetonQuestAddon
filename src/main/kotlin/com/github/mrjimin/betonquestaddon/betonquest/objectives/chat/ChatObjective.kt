package com.github.mrjimin.betonquestaddon.betonquest.objectives.chat

import com.github.mrjimin.betonquestaddon.util.toPlainText
import io.papermc.paper.event.player.AsyncChatEvent
import org.betonquest.betonquest.BetonQuest
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.id.ObjectiveID
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.quest.objective.variable.VariableObjective
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

class ChatObjective(
    instruction: Instruction,
    private val cancel: Boolean,
    private val variable: Variable<Map.Entry<ObjectiveID, String>>?
) : Objective(instruction), Listener {

    override fun start() {
        Bukkit.getPluginManager().registerEvents(this, BetonQuest.getInstance())
    }

    override fun stop() {
        HandlerList.unregisterAll(this)
    }

    override fun getDefaultDataInstruction(): String = ""

    override fun getProperty(name: String, profile: Profile): String = ""

    @EventHandler(ignoreCancelled = true)
    fun AsyncChatEvent.onChat() {
        val onlineProfile = profileProvider.getProfile(player)
        if (!containsPlayer(onlineProfile) || !checkConditions(onlineProfile)) return

        if (cancel) {
            isCancelled = true
        }

        if (variable != null) {
            qeHandler.handle {
                val variableEntry = variable.getValue(onlineProfile)
                val objective = BetonQuest.getInstance().questTypeApi.getObjective(variableEntry.key)
                if (objective is VariableObjective) {
                    if (!objective.store(onlineProfile, variableEntry.value, message().toPlainText())) {
                        throw QuestException(
                            "Can't store value in variable objective '${variableEntry.key.fullID}' because it is not active for the player!"
                        )
                    }
                } else {
                    throw QuestException(
                        "Can't store value in variable objective '${variableEntry.key.fullID}' because it is not a variable objective!"
                    )
                }
            }
        }

        completeObjective(onlineProfile)
    }
}