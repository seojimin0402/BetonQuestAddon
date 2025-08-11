package com.github.mrjimin.betonquestaddon.compatibility.bqaddon.items

import com.github.mrjimin.betonquestaddon.util.toLegacy
import net.kyori.adventure.text.Component
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItem
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class BQAddonItem(
    private val type: Material,
    private val itemName: Component,
    private val lore: List<Component>
) : QuestItem {



    override fun getName(): String {
        return itemName.toLegacy()
    }

    override fun getLore(): List<String> {
        return lore.map { it.toLegacy() }
    }

    override fun generate(amount: Int, profile: Profile?): ItemStack {
        val item = ItemStack(type, amount)
        val meta = item.itemMeta ?: return item

        meta.itemName(itemName)
        meta.lore(lore)

        item.itemMeta = meta
        return item
    }

    override fun matches(itemStack: ItemStack?): Boolean {
        if (itemStack == null) return false
        if (itemStack.type != type) return false

        val meta = itemStack.itemMeta ?: return false

        if (meta.itemName() != itemName) return false

        val itemLore = meta.lore() ?: return false
        if (itemLore != lore) return false

        return true
    }
}
