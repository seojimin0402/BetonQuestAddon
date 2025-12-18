package com.github.mrjimin.betonquestaddon.compatibility.itemsadder

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.event.ItemsAdderEventFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.event.PlayAnimationFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item.ItemsAdderItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item.ItemsAdderQuestItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.ItemsAdderObjectiveFactory
import com.github.mrjimin.betonquestaddon.util.event.ActionType
import com.github.mrjimin.betonquestaddon.util.event.TargetType
import org.betonquest.betonquest.api.BetonQuestApi

class ItemsAdderIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        val data = api.primaryServerThreadData
        val questRegistries = api.questRegistries
        val loggerFactory = api.loggerFactory

        val itemRegistry = api.featureRegistries.item()
        itemRegistry.register("itemsAdder", ItemsAdderItemFactory())
        itemRegistry.registerSerializer("itemsAdder", ItemsAdderQuestItemSerializer())

        val event = questRegistries.event()
        event.register(
            "itemsAdderBlockAt",
            ItemsAdderEventFactory(data, TargetType.BLOCK)
        )
        event.register(
            "itemsAdderFurnitureAt",
            ItemsAdderEventFactory(data, TargetType.FURNITURE)
        )
        event.register(
            "itemsAdderPlayAnimation",
            PlayAnimationFactory(data, loggerFactory)
        )

        val objective = questRegistries.objective()
        objective.register("itemsAdderBlockBreak", ItemsAdderObjectiveFactory(TargetType.BLOCK, ActionType.BREAK))
        objective.register("itemsAdderBlockPlace", ItemsAdderObjectiveFactory(TargetType.BLOCK, ActionType.PLACE))
        objective.register("itemsAdderBlockInteract", ItemsAdderObjectiveFactory(TargetType.BLOCK, ActionType.INTERACT))

        objective.register("itemsAdderFurnitureBreak", ItemsAdderObjectiveFactory(TargetType.FURNITURE, ActionType.BREAK))
        objective.register("itemsAdderFurniturePlace", ItemsAdderObjectiveFactory(TargetType.FURNITURE, ActionType.PLACE))
        objective.register("itemsAdderFurnitureInteract", ItemsAdderObjectiveFactory(TargetType.FURNITURE, ActionType.INTERACT))
    }
}