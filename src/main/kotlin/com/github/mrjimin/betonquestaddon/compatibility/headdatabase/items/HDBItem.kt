package com.github.mrjimin.betonquestaddon.compatibility.headdatabase.items

import com.github.mrjimin.betonquestaddon.util.toLegacy
import me.arcaniax.hdb.api.HeadDatabaseAPI
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItem
import org.bukkit.inventory.ItemStack

class HDBItem(
    private val itemId: String
) : QuestItem {

    private val hdbItemMeta = HeadDatabaseAPI().getItemHead(itemId).itemMeta

    override fun getName(): String? {
        return hdbItemMeta?.itemName()?.toLegacy()
    }

    override fun getLore(): List<String?>? {
        return hdbItemMeta?.lore()?.map { it.toLegacy() }
    }

    override fun generate(int: Int, profile: Profile?): ItemStack? {
        val item = HeadDatabaseAPI().getItemHead(itemId).clone()
        item.amount = int
        return item
    }

    override fun matches(item: ItemStack?): Boolean {
        val id = HeadDatabaseAPI().getItemID(item)
        return id == itemId
    }
}