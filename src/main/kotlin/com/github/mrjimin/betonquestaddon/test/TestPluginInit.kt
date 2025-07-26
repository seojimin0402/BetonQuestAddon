package com.github.mrjimin.betonquestaddon.test

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import com.github.mrjimin.betonquestaddon.test.listener.TestListener

class TestPluginInit(
    private val plugin: BetonQuestAddonPlugin
) {

    init {
        registerEvents()
    }

    fun registerEvents() {
        plugin.server.pluginManager.registerEvents(TestListener(), plugin)
    }
}