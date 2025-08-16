package com.github.mrjimin.betonquestaddon.betonquest.items

import com.github.mrjimin.betonquestaddon.api.BQAddonItems
import com.github.mrjimin.betonquestaddon.item.ItemBuilder
import com.github.mrjimin.betonquestaddon.util.toLegacy
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.item.QuestItem
import org.bukkit.inventory.ItemStack

class BQAddonItem(
    private val itemId: String
) : QuestItem {

    private val itemStack: ItemBuilder = BQAddonItems.itemFromId(itemId) ?:
        throw QuestException("Invalid or missing BetonQuestAddon Item ID: '$itemId'")
    private val itemMeta = itemStack.build().clone().itemMeta

    override fun getName(): String {
        return itemMeta.itemName().toLegacy()
    }

    override fun getLore(): List<String?>? {
        return itemMeta.lore()?.map { it.toLegacy() }
    }

    override fun generate(int: Int, profile: Profile?): ItemStack? {
        val item = BQAddonItems.itemFromId(itemId) ?: return null
        return item.amount(int).build()
    }

    override fun matches(item: ItemStack?): Boolean {
        val matchedId = BQAddonItems.idFromItem(item)
        return matchedId == itemId
    }
}
