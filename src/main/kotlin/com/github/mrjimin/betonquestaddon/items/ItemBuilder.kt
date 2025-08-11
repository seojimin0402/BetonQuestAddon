package com.github.mrjimin.betonquestaddon.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemRarity
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

class ItemBuilder(private var itemStack: ItemStack) {

    private var itemMeta: ItemMeta = itemStack.itemMeta

    constructor(material: Material) : this(ItemStack(material))

    fun <T, Z : Any> setCustomTag(key: NamespacedKey, type: PersistentDataType<T, Z>, value: Z): ItemBuilder {
        itemMeta.persistentDataContainer.set(key, type, value)
        itemStack.itemMeta = itemMeta
        return this
    }

    fun <T : Any, Z : Any> getCustomTag(key: NamespacedKey, type: PersistentDataType<T, Z>): Z? {
        return itemMeta.persistentDataContainer.get(key, type)
    }

    fun hasCustomTag(): Boolean {
        return !itemMeta.persistentDataContainer.isEmpty
    }

    fun <T, Z : Any> addCustomTag(key: NamespacedKey, type: PersistentDataType<T, Z>, value: Z): ItemBuilder {
        itemMeta.persistentDataContainer.set(key, type, value)
        itemStack.itemMeta = itemMeta
        return this
    }

    fun removeCustomTag(key: NamespacedKey): ItemBuilder {
        itemMeta.persistentDataContainer.remove(key)
        itemStack.itemMeta = itemMeta
        return this
    }

    fun type(type: Material): ItemBuilder {
        itemStack = ItemStack(type)
        itemMeta = itemStack.itemMeta
        return this
    }

    fun type(type: ItemStack): ItemBuilder {
        itemStack = type.clone()
        itemMeta = itemStack.itemMeta
        return this
    }

    fun amount(amount: Int): ItemBuilder {
        itemStack.amount = amount
        return this
    }

    fun itemName(itemName: Component): ItemBuilder {
        val styledName = itemName.decorationIfAbsent(
            TextDecoration.ITALIC,
            TextDecoration.State.FALSE
        ).colorIfAbsent(NamedTextColor.WHITE)
        itemMeta.itemName(styledName)
        return this
    }

    fun lore(lore: List<Component>): ItemBuilder {
        val styledLore = lore.map { it.decorationIfAbsent(
            TextDecoration.ITALIC, TextDecoration.State.FALSE
        ) }
        itemMeta.lore(styledLore)
        return this
    }

    fun unbreakable(unbreakable: Boolean): ItemBuilder {
        itemMeta.isUnbreakable = unbreakable
        return this
    }

    fun addItemFlags(vararg itemFlags: ItemFlag): ItemBuilder {
        itemMeta.itemFlags.addAll(itemFlags)
        return this
    }

    fun addEnchantments(enchantments: Map<Enchantment?, Int?>): ItemBuilder {
        enchantments.forEach { (enchant, level) ->
            if (enchant != null) {
                itemMeta.addEnchant(enchant, level ?: 1, true)
            }
        }
        return this
    }

    fun rarity(rarity: ItemRarity): ItemBuilder {
        itemMeta.setRarity(rarity)
        return this
    }

    //
    fun hideTooltip(hideTooltip: Boolean): ItemBuilder {
        itemMeta.isHideTooltip = hideTooltip
        return this
    }

    fun unstackable(unstackable: Boolean): ItemBuilder {
        if (unstackable) maxStackSize(1)
        return this
    }

    fun maxStackSize(size: Int): ItemBuilder {
        itemMeta.setMaxStackSize(size)
        return this
    }

    //

    fun tooltipStyle(tooltipStyle: NamespacedKey): ItemBuilder {
        itemMeta.tooltipStyle = tooltipStyle
        return this
    }

    fun itemModel(itemModel: NamespacedKey): ItemBuilder {
        itemMeta.itemModel = itemModel
        return this
    }

    fun enchantable(enchantable: Int): ItemBuilder {
        itemMeta.setEnchantable(enchantable)
        return this
    }

    fun glider(glider: Boolean): ItemBuilder {
        itemMeta.isGlider = glider
        return this
    }

    fun build(): ItemStack {
        itemStack.itemMeta = itemMeta
        return itemStack
    }

}