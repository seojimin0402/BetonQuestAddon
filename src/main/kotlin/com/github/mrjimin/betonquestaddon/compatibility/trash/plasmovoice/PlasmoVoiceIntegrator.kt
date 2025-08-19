//package com.github.mrjimin.betonquestaddon.compatibility.trash.plasmovoice
//
//import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
//import com.github.mrjimin.betonquestaddon.compatibility.trash.plasmovoice.events.PVTestPlayFactory
//import com.github.mrjimin.betonquestaddon.compatibility.trash.plasmovoice.events.PVTestStopFactory
//
//object PlasmoVoiceIntegrator : BQAddonIntegrator() {
//    override fun hook() {
//        event.apply {
//            register("pvPlay", PVTestPlayFactory(data))
//            register("pvStop", PVTestStopFactory(data))
//        }
//    }
//}