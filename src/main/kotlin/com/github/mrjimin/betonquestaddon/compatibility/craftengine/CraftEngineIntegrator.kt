package com.github.mrjimin.betonquestaddon.compatibility.craftengine

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.event.CraftEngineEventFactory
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.item.CraftEngineItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.item.CraftEngineQuestItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.CraftEngineObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.NexoObjectiveFactory
import com.github.mrjimin.betonquestaddon.conditions.BaseConditionFactory
import com.github.mrjimin.betonquestaddon.util.event.ActionType
import com.github.mrjimin.betonquestaddon.util.event.TargetType
import net.momirealms.craftengine.bukkit.api.BukkitAdaptors
import org.betonquest.betonquest.api.BetonQuestApi

class CraftEngineIntegrator : ICompatibility {

    override fun hook(api: BetonQuestApi) {
        val data = api.primaryServerThreadData
        val questRegistries = api.questRegistries

        val itemRegistry = api.featureRegistries.item()
        itemRegistry.register("craftEngine", CraftEngineItemFactory())
        itemRegistry.registerSerializer("craftEngine", CraftEngineQuestItemSerializer())

        val condition = questRegistries.condition()
        condition.registerCombined(
            "craftEngineBlock",
            BaseConditionFactory(data) {  location ->
                BukkitAdaptors.adapt(location.block).id().toString()
            }
        )
//        condition.registerCombined(
//            "craftFurniture",
//            CustomConditionFactory(data) { location ->
//                CraftEngineFurniture.
//                BukkitAdaptors.adapt(location.block).id().toString()
//            }
//        )

        val event = questRegistries.event()
        event.register(
            "craftEngineBlockAt",
            CraftEngineEventFactory(data, TargetType.BLOCK)
        )
        event.register(
            "craftEngineFurnitureAt",
            CraftEngineEventFactory(data, TargetType.FURNITURE)
        )


        val objective = questRegistries.objective()
        objective.register("craftEngineFurnitureBreak", CraftEngineObjectiveFactory(TargetType.FURNITURE, ActionType.BREAK))
        objective.register("craftEngineFurnitureInteract", CraftEngineObjectiveFactory(TargetType.FURNITURE, ActionType.INTERACT))
    }

}