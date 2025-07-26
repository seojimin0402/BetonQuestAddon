package com.github.mrjimin.betonquestaddon.compatibility.craftengine

import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.conditions.CeBlockConditionFactory
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.events.CeSetBlockAtEventFactory
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.items.CeItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.items.CeItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.block.CeBlockBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.block.CeBlockInteractObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.block.CeBlockPlaceObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.furniture.CeFurnitureInteractObjectiveFactory

object CraftEngineIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.registerCombined("ceBlock", CeBlockConditionFactory(data))
        event.apply {
            register("ceBlockAt", CeSetBlockAtEventFactory(data))
            // register("ceFurnitureAt", CeSetFurnitureAtEventFactor(data))
        }
        objective.apply {
            register("ceBlockPlace", CeBlockPlaceObjectiveFactory(loggerFactory))
            register("ceBlockBreak", CeBlockBreakObjectiveFactory(loggerFactory))
            register("ceBlockInteract", CeBlockInteractObjectiveFactory(loggerFactory))

            register("ceFurnitureInteract", CeFurnitureInteractObjectiveFactory(loggerFactory))
        }
        registerItem("ce", CeItemFactory, CeItemSerializer)
    }
}