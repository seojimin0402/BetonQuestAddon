package com.github.seojimin0402.betonquestaddon.compatibility.nexo.objectives

import com.github.seojimin0402.betonquestaddon.util.event.ActionType
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.entity.Player
import org.bukkit.event.Listener

abstract class NexoObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>?,
    message: String,
    protected val item: Variable<String>,
    protected val action: ActionType
) : CountingObjective(instruction, targetAmount, message), Listener {

    protected fun handle(player: Player, itemId: String?) {
        if (itemId == null) return

        val profile = profileProvider.getProfile(player)
        qeHandler.handle {
            if (
                containsPlayer(profile) &&
                item.getValue(profile) == itemId &&
                checkConditions(profile)
            ) {
                getCountingData(profile).progress()
                completeIfDoneOrNotify(profile)
            }
        }
    }
}

