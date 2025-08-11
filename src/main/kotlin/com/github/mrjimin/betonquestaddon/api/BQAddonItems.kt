package com.github.mrjimin.betonquestaddon.api

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import com.github.mrjimin.betonquestaddon.config.ConfigsManager
import com.github.mrjimin.betonquestaddon.items.ItemBuilder
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.io.File

object BQAddonItems {

    val ITEM_ID = NamespacedKey(BetonQuestAddonPlugin.instance, "id")

    private val itemsMap = mutableMapOf<File, MutableMap<String, ItemBuilder>>()
    private val idToItemBuilder = mutableMapOf<String, ItemBuilder>()

    fun loadItems(configsManager: ConfigsManager) {
        val parsed = configsManager.parseItemConfig()

        itemsMap.clear()
        idToItemBuilder.clear()

        itemsMap.putAll(parsed.mapValues { it.value.toMutableMap() })

        for (itemBuilders in itemsMap.values) {
            for ((id, builder) in itemBuilders) {
                idToItemBuilder[id] = builder
            }
        }

    }

    fun itemFromId(id: String): ItemBuilder? = idToItemBuilder[id]

    fun idFromItem(itemStack: ItemStack): String? =
        itemStack.itemMeta?.persistentDataContainer?.get(ITEM_ID, PersistentDataType.STRING)

    fun getItems(): Collection<ItemBuilder> = idToItemBuilder.values.toList()

    fun getIds(): List<String> = idToItemBuilder.keys.toList().sorted()

    fun exists(id: String): Boolean = itemFromId(id) != null

    fun exists(itemStack: ItemStack?): Boolean {
        if (itemStack == null) return false
        val id = idFromItem(itemStack) ?: return false
        return exists(id)
    }
}
