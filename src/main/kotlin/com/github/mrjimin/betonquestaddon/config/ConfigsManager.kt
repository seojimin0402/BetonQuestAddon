package com.github.mrjimin.betonquestaddon.config

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import com.github.mrjimin.betonquestaddon.api.BQAddonItems
import com.github.mrjimin.betonquestaddon.api.BQAddonItems.getIds
import com.github.mrjimin.betonquestaddon.items.ItemBuilder
import com.github.mrjimin.betonquestaddon.items.ItemParser
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class ConfigsManager(private val plugin: BetonQuestAddonPlugin) {
    private val itemsFolder = File(plugin.dataFolder, "items")

    fun reload() {
        plugin.saveDefaultConfig()
        plugin.reloadConfig()

        BQAddonItems.loadItems(this)
        println(BQAddonItems.getIds())
        println(BQAddonItems.exists(getIds().first()))
    }

    fun parseItemConfig(): Map<File, MutableMap<String, ItemBuilder>> {
        if (!itemsFolder.exists()) itemsFolder.mkdirs()

        return itemsFolder.walkTopDown()
            .filter { it.isFile && it.extension == "yml" }
            .associateWith { file ->
                val config = YamlConfiguration.loadConfiguration(file)
                config.getKeys(false).associateWith { itemId ->
                    val section = config.getConfigurationSection(itemId)
                    ItemParser(section!!).buildItem(itemId)
                }.toMutableMap()
            }
    }
}