package com.github.mrjimin.betonquestaddon.compatibility.coinsengine

import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.coinsengine.conditions.CoinsConditionFactory
import com.github.mrjimin.betonquestaddon.compatibility.coinsengine.events.CoinsEventFactory

object CoinsEngineIntegrator : BQAddonIntegrator() {
    override fun hook() {
        event.register("coins", CoinsEventFactory(loggerFactory, data, pluginMessage, variableProcessor))
        condition.register("coins", CoinsConditionFactory(data))
    }
}
