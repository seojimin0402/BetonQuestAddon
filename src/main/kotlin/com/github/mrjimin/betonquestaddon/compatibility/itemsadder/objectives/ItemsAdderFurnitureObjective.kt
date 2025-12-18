package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives

import com.github.mrjimin.betonquestaddon.util.event.ActionType
import dev.lone.itemsadder.api.Events.*
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler

class ItemsAdderFurnitureObjective(
    instruction: Instruction,
    amount: Variable<Number>?,
    message: String,
    item: Variable<String>,
    actionType: ActionType,
    isCancelled: Variable<Boolean>?
) : ItemsAdderObjective(instruction, message, amount, item, actionType, isCancelled) {

    @EventHandler(ignoreCancelled = true)
    fun FurnitureBreakEvent.onBreak() {
        handle(ActionType.BREAK, player, furniture?.entity!!)
    }

    @EventHandler(ignoreCancelled = true)
    fun FurniturePlacedEvent.onPlace() {
        handle(ActionType.PLACE, player, furniture?.entity!!)
    }

    @EventHandler(ignoreCancelled = true)
    fun FurnitureInteractEvent.onInteract() {
        handle(ActionType.INTERACT, player, furniture?.entity!!)
    }

}