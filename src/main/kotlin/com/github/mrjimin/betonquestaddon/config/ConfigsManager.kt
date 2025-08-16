package com.github.mrjimin.betonquestaddon.config

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import com.github.mrjimin.betonquestaddon.api.BQAddonItems
import com.github.mrjimin.betonquestaddon.item.ItemBuilder
import com.github.mrjimin.betonquestaddon.item.ItemParser
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class ConfigsManager(private val plugin: BetonQuestAddonPlugin) {
    private val itemsFolder = File(plugin.dataFolder, "items")

    fun reload() {
        plugin.saveDefaultConfig()
        plugin.reloadConfig()
        BQAddonItems.loadItems(this)
    }

    fun parseItemConfig(): Map<File, MutableMap<String, ItemBuilder>> {
        if (!itemsFolder.exists()) itemsFolder.mkdirs()

        return itemsFolder.walkTopDown()
            .filter { it.isFile && it.extension.equals("yml", ignoreCase = true) }
            .associateWith { file ->
                val config = YamlConfiguration.loadConfiguration(file)

                config.getKeys(false).mapNotNull { itemId ->
                    config.getConfigurationSection(itemId)?.let { section ->
                        itemId to ItemParser(section).buildItem(itemId)
                    }
                }.toMap().toMutableMap()
            }
    }
}