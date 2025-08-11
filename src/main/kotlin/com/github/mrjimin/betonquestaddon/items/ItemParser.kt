package com.github.mrjimin.betonquestaddon.items

import com.github.mrjimin.betonquestaddon.api.BQAddonItems
import com.github.mrjimin.betonquestaddon.util.toMiniMessage
import io.papermc.paper.registry.RegistryAccess
import io.papermc.paper.registry.RegistryKey
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemRarity
import org.bukkit.persistence.PersistentDataType

class ItemParser(private val section: ConfigurationSection) {

    private val type: Material = runCatching {
        Material.matchMaterial(section.getString("material") ?: "STONE")
            ?: Material.STONE
    }.getOrDefault(Material.STONE)

    fun buildItem(itemId: String): ItemBuilder {
        val item = ItemBuilder(type)
        item.setCustomTag(BQAddonItems.ITEM_ID, PersistentDataType.STRING, itemId)
        return applyConfig(item)
    }

    fun applyConfig(item: ItemBuilder): ItemBuilder {

        item.type(type)

        section.getString("item_name")
            ?.takeIf { it.isNotBlank() }
            ?.let { item.itemName(it.toMiniMessage()) }

        section.getStringList("lore")
            .takeIf { it.isNotEmpty() }
            ?.map { it.toMiniMessage() }
            ?.let(item::lore)

        item.unbreakable(section.getBoolean("unbreakable", false))
        item.unstackable(section.getBoolean("unstackable", false))

        parseDataComponents(item)
        parseVanillaSections(item)

        return item
    }

    private fun parseVanillaSections(item: ItemBuilder) {
        section.getStringList("ItemFlags")
            .mapNotNull { runCatching { ItemFlag.valueOf(it.uppercase()) }.getOrNull() }
            .takeIf { it.isNotEmpty() }
            ?.let { item.addItemFlags(*it.toTypedArray()) }

        section.getConfigurationSection("Enchantments")?.let { enchSection ->
            val enchantments = enchSection.getKeys(false).associate { enchName ->
                val enchant = RegistryAccess.registryAccess()
                    .getRegistry(RegistryKey.ENCHANTMENT)
                    .get(NamespacedKey.minecraft(enchName.lowercase()))
                enchant to enchSection.getInt(enchName, 1)
            }

            if (enchantments.isNotEmpty()) {
                item.addEnchantments(enchantments)
            }
        }

        section.getString("rarity")
            ?.uppercase()
            ?.let { runCatching { ItemRarity.valueOf(it) }.getOrNull() }
            ?.let(item::rarity)

        item.glider(section.getBoolean("Glider", false))

        item.maxStackSize(section.getInt("max_stack_size", 64))

    }

    private fun parseDataComponents(item: ItemBuilder) {
        section.getConfigurationSection("Components")
            ?.let { handleLegacyComponents(item, it) }
    }

    fun handleLegacyComponents(item: ItemBuilder, components: ConfigurationSection) {
        components.getBoolean("hide_tooltip", false).let { item.hideTooltip(it) }

        components.getString("tooltip_style")
            ?.let(NamespacedKey::fromString)
            ?.let(item::tooltipStyle)

        components.getString("item_model")
            ?.let(NamespacedKey::fromString)
            ?.let(item::itemModel)

        components.getInt("enchantable")
            .takeIf { it > 0 }
            ?.let(item::enchantable)

    }

}