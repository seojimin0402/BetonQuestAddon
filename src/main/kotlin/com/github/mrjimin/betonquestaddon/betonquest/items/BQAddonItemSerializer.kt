package com.github.mrjimin.betonquestaddon.betonquest.items

import com.github.mrjimin.betonquestaddon.api.BQAddonItems
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.item.QuestItemSerializer
import org.bukkit.inventory.ItemStack

object BQAddonItemSerializer : QuestItemSerializer {
    override fun serialize(itemStack: ItemStack): String {
        if (!itemStack.hasItemMeta())
            throw QuestException("ItemStack is null or missing metadata.")

        val id = BQAddonItems.idFromItem(itemStack) ?:
        throw QuestException("The item does not contain a valid BetonQuestAddon ID.")

        return id
    }
}