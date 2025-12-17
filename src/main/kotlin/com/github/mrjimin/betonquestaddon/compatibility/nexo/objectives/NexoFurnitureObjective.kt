package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives

import com.github.mrjimin.betonquestaddon.util.event.ActionType
import com.nexomc.nexo.api.events.furniture.*
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler

class NexoFurnitureObjective(
    instruction: Instruction,
    amount: Variable<Number>?,
    message: String,
    item: Variable<String>,
    actionType: ActionType,
    isCancelled: Variable<Boolean>?
) : NexoObjective(instruction, message, amount, item, actionType, isCancelled) {

    @EventHandler(ignoreCancelled = true)
    fun NexoFurnitureBreakEvent.onBreak() {
        handle(ActionType.BREAK, player, baseEntity)
    }

    @EventHandler(ignoreCancelled = true)
    fun NexoFurniturePlaceEvent.onPlace() {
        handle(ActionType.PLACE, player, baseEntity)
    }

    @EventHandler(ignoreCancelled = true)
    fun NexoFurnitureInteractEvent.onInteract() {
        handle(ActionType.INTERACT, player, baseEntity)
    }

}
