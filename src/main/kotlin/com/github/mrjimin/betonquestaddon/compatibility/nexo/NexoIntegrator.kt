package com.github.mrjimin.betonquestaddon.compatibility.nexo

import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.nexo.conditions.NxBlockConditionFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.events.NxSetBlockAtEventFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.items.NxItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.items.NxItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.NxBlockBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.NxBlockPlaceObjectiveFactory

object NexoIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.registerCombined("nxBlock", NxBlockConditionFactory(data))
        event.register("nxBlockAt", NxSetBlockAtEventFactory(data))
        objective.apply {
            register("nxBlockPlace", NxBlockPlaceObjectiveFactory(loggerFactory))
            register("nxBlockBreak", NxBlockBreakObjectiveFactory(loggerFactory))
        }
        registerItem("nexo", NxItemFactory, NxItemSerializer)
    }
}