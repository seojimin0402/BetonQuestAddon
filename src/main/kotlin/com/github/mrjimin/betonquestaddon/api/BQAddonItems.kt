package com.github.mrjimin.betonquestaddon.api

import com.github.mrjimin.betonquestaddon.config.ConfigsManager
import com.github.mrjimin.betonquestaddon.item.ItemBuilder
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.io.File

object BQAddonItems {

    val ITEM_ID = NamespacedKey("betonquestaddon", "id")
    val itemsMap = mutableMapOf<File, MutableMap<String, ItemBuilder>>()
    val idToItemBuilder = mutableMapOf<String, ItemBuilder>()

    fun loadItems(configsManager: ConfigsManager) {
        val parsedItems = configsManager.parseItemConfig()

        itemsMap.clear()
        idToItemBuilder.clear()

        itemsMap.putAll(parsedItems)
        parsedItems.values.forEach { idToItemBuilder.putAll(it) }
    }

    fun idFromItem(item: ItemBuilder): String? = item.getPDC(ITEM_ID, PersistentDataType.STRING)

    fun idFromItem(item: ItemStack?): String? = item?.itemMeta?.persistentDataContainer[ITEM_ID, PersistentDataType.STRING]

    fun itemFromId(id: String?): ItemBuilder? = idToItemBuilder[id]

    fun exists(id: String?): Boolean = itemFromId(id) != null

    fun exists(item: ItemStack?): Boolean = idFromItem(item)?.let { exists(it) } ?: false

    fun allIds(): List<String> = idToItemBuilder.keys.asSequence().sorted().toList()  // idToItemBuilder.keys.toList().sorted()

    fun allItems(): List<ItemBuilder> = idToItemBuilder.values.toList()

    fun firstItemsFromFile(): List<Pair<File, ItemBuilder>> =
        itemsMap.mapNotNull { (file, map) ->
            map.values.firstOrNull()?.let { file to it }
        }

    fun allItemsFromFile(file: File): List<ItemBuilder> =
        itemsMap[file]?.values?.toList() ?: emptyList()
}
