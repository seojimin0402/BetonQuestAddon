package com.github.mrjimin.betonquestaddon.compatibility.placeholderapi

import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.placeholderapi.conditions.PAPIConditionFactory

object PAPIIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.register("PAPI", PAPIConditionFactory(data))
    }

}