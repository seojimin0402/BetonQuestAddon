package com.github.mrjimin.betonquestaddon.compatibility

import com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.AEIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.coinsengine.CoinsEngineIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.CraftEngineIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.headdatabase.HDBIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.ItemsAdderIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.lands.LandsIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.nexo.NexoIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.placeholderapi.PAPIIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.worldguard.WorldGuardIntegrator
import com.github.mrjimin.betonquestaddon.util.server.checkPlugin
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.api.quest.QuestTypeRegistries
import org.betonquest.betonquest.config.PluginMessage
import org.betonquest.betonquest.kernel.processor.quest.VariableProcessor

class BQAddonIntegratorHandler(
    val loggerFactory: BetonQuestLogger,
    val data: PrimaryServerThreadData,
    val pluginMessage: PluginMessage,
    val variableProcessor: VariableProcessor,
    val questRegistries: QuestTypeRegistries
) {

    companion object {
        private val hookedPlugins = mutableSetOf<String>()

        fun addHookedPlugin(name: String) {
            hookedPlugins.add(name)
        }

        fun getHookedPlugins(): List<String> = hookedPlugins.toList()
        fun clearHookedPlugins() = hookedPlugins.clear()
    }

    private val integrators = mutableMapOf<String, BQAddonIntegrator>()

    init {
        registerCompatiblePlugins()
        hookAll()
    }

    private fun register(pluginName: String, integrator: BQAddonIntegrator) {
        integrators[pluginName] = integrator
    }

    private fun hookAll() {
        integrators.forEach { (pluginName, integrator) ->
            if (!pluginName.checkPlugin()) {
                loggerFactory.debug("Plugin $pluginName not found, skipping integration.")
                return@forEach
            }
            runCatching {
                integrator.hook()
                addHookedPlugin(pluginName)
                loggerFactory.info("Successfully hooked into $pluginName.")
            }.onFailure { ex ->
                loggerFactory.warn("Failed to hook into $pluginName: ${ex.message}", ex)
            }
        }
    }

    private fun registerCompatiblePlugins() {
        register("CoinsEngine", CoinsEngineIntegrator)
        register("Nexo", NexoIntegrator)
        register("ItemsAdder", ItemsAdderIntegrator)
        register("CraftEngine", CraftEngineIntegrator)
        register("HeadDatabase", HDBIntegrator)
        register("AdvancedEnchantments", AEIntegrator)
        register("PlaceholderAPI", PAPIIntegrator)
        register("Lands", LandsIntegrator)
        register("WorldGuard", WorldGuardIntegrator)
    }

}
