package com.github.mrjimin.betonquestaddon.compatibility.headdatabase

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.headdatabase.conditions.HDBBlockFactory
import com.github.mrjimin.betonquestaddon.compatibility.headdatabase.items.HDBItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.headdatabase.items.HDBItemSerializer

object HDBIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.registerCombined("hdbBlock", HDBBlockFactory(data))
        BetonQuestAddon.registerItem("hdb", HDBItemFactory, HDBItemSerializer)
    }
}