package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives

import com.github.mrjimin.betonquestaddon.util.event.ActionType
import dev.lone.itemsadder.api.Events.*
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler

class ItemsAdderBlockObjective(
    instruction: Instruction,
    amount: Variable<Number>?,
    message: String,
    item: Variable<String>,
    actionType: ActionType,
    isCancelled: Variable<Boolean>?
) : ItemsAdderObjective(instruction, message, amount, item, actionType, isCancelled) {

    @EventHandler(ignoreCancelled = true)
    fun CustomBlockBreakEvent.onBreak() {
        handle(ActionType.BREAK, player, block)
    }

    @EventHandler(ignoreCancelled = true)
    fun CustomBlockPlaceEvent.onPlace() {
        handle(ActionType.PLACE, player, block)
    }

    @EventHandler(ignoreCancelled = true)
    fun CustomBlockInteractEvent.onInteract() {
        handle(ActionType.INTERACT, player, blockClicked)
    }
}