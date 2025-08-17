package com.github.mrjimin.betonquestaddon.compatibility.plasmovoice

import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.plasmovoice.events.PVPlayFactory
import com.github.mrjimin.betonquestaddon.compatibility.plasmovoice.events.PVStopFactory

object PlasmoVoiceIntegrator : BQAddonIntegrator() {
    override fun hook() {
        event.apply {
            register("pvPlay", PVPlayFactory(data))
            register("pvStop", PVStopFactory(data))
        }
    }
}