package com.github.seojimin0402.betonquestaddon.compatibility.nexo.objectives

import com.github.seojimin0402.betonquestaddon.util.event.ActionType
import com.nexomc.nexo.api.NexoFurniture
import com.nexomc.nexo.api.events.furniture.NexoFurnitureBreakEvent
import com.nexomc.nexo.api.events.furniture.NexoFurnitureInteractEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler

class NexoFurnitureObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>?,
    message: String,
    item: Variable<String>,
    action: ActionType
) : NexoObjective(instruction, targetAmount, message, item, action) {

    @EventHandler(ignoreCancelled = true)
    fun NexoFurnitureBreakEvent.onBreak() {
        if (action != ActionType.BREAK) return
        val id = NexoFurniture.furnitureMechanic(baseEntity)?.itemID
        handle(player, id)
    }

    @EventHandler(ignoreCancelled = true)
    fun NexoFurnitureInteractEvent.onInteract() {
        if (action != ActionType.INTERACT) return
        val id = NexoFurniture.furnitureMechanic(baseEntity)?.itemID
        handle(player, id)
    }
}
