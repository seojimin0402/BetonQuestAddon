package com.github.mrjimin.betonquestaddon.item

import com.github.mrjimin.betonquestaddon.api.BQAddonItems
import com.github.mrjimin.betonquestaddon.util.server.MinecraftVersion
import com.github.mrjimin.betonquestaddon.util.toMiniMessage
import io.papermc.paper.registry.RegistryAccess
import io.papermc.paper.registry.RegistryKey
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Tag
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.damage.DamageType
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemRarity
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.tag.DamageTypeTags

class ItemParser(private val section: ConfigurationSection) {

    private val type: Material = runCatching {
        Material.matchMaterial(section.getString("material") ?: "STONE")
            ?: Material.STONE
    }.getOrDefault(Material.STONE)

    fun buildItem(id: String? = null): ItemBuilder {
        val builder = ItemBuilder(type)

        applyBasicConfig(builder)
        applyVanillaConfig(builder)
        applyComponents(builder)

        id?.let { builder.setPDC(BQAddonItems.ITEM_ID, PersistentDataType.STRING, it) }

        return builder
    }

    private fun applyBasicConfig(item: ItemBuilder) {
        section.getString("item_name")?.takeIf { it.isNotBlank() }?.let { item.itemName(it.toMiniMessage()) }

        section.getStringList("lore").takeIf { it.isNotEmpty() }?.map { it.toMiniMessage() }?.let(item::lore)

        item.unbreakable(section.getBoolean("unbreakable", false))

        item.unstackable(section.getBoolean("unstackable", false))
    }

    private fun applyVanillaConfig(item: ItemBuilder) {
        section.getStringList("ItemFlags")
            .mapNotNull { runCatching { ItemFlag.valueOf(it.uppercase()) }.getOrNull() }
            .takeIf { it.isNotEmpty() }?.let { item.addItemFlags(*it.toTypedArray()) }

        section.getConfigurationSection("Enchantments")?.let { enchSection ->
            val enchantments = enchSection.getKeys(false).associate { enchName ->
                val enchant = RegistryAccess.registryAccess()
                    .getRegistry(RegistryKey.ENCHANTMENT)
                    .get(NamespacedKey.minecraft(enchName.lowercase()))
                enchant to enchSection.getInt(enchName, 1)
            }
            if (enchantments.isNotEmpty()) item.addEnchantments(enchantments)
        }

        section.getString("rarity")?.uppercase()?.let { runCatching { ItemRarity.valueOf(it) }.getOrNull() }?.let(item::rarity)

        if (MinecraftVersion.V1_21_3.isAtOrAbove()) item.glider(section.getBoolean("Glider", false))

        item.maxStackSize(section.get("max_stack_size") as? Int)

        if (MinecraftVersion.V1_21_3.isAtOrAbove()) section.getString("damage_resistant")?.uppercase()?.let { tagName ->
            DamageTypeTagRegistry.tags[tagName]?.let(item::damageResistant)
        }
    }

    private fun applyComponents(item: ItemBuilder) {
        section.getConfigurationSection("Components")?.let { components ->
            components.getBoolean("hide_tooltip", false).let { item.hideTooltip(it) }
            if (MinecraftVersion.V1_21_3.isAtOrAbove()) components.getString("tooltip_style")?.let(NamespacedKey::fromString)?.let(item::tooltipStyle)
            if (MinecraftVersion.V1_21_3.isAtOrAbove()) components.getString("item_model")?.let(NamespacedKey::fromString)?.let(item::itemModel)
            if (MinecraftVersion.V1_21_3.isAtOrAbove()) components.getInt("enchantable").takeIf { it > 0 }?.let(item::enchantable)
        }
    }

}