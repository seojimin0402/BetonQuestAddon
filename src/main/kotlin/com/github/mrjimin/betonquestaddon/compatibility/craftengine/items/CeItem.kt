package com.github.mrjimin.betonquestaddon.compatibility.craftengine.items

import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook
import com.github.mrjimin.betonquestaddon.util.toLegacy
import net.kyori.adventure.text.Component
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItem
import org.bukkit.inventory.ItemStack

class CeItem(
    private val itemId: String
) : QuestItem {

    private val customItemMeta = CraftEngineHook.itemFromId(itemId)?.buildItemStack()?.itemMeta

    override fun getName(): Component? {
        return customItemMeta?.itemName()
    }

    override fun getLore(): List<Component?>? {
        return customItemMeta?.lore()?.map { it }
    }

    override fun generate(int: Int, profile: Profile?): ItemStack? {
        val item = CraftEngineHook.itemFromId(itemId) ?: return null
        return item.buildItemStack()
    }

    override fun matches(item: ItemStack?): Boolean {
        val matchedId = CraftEngineHook.idFromItem(item)
        return matchedId == itemId
    }
}