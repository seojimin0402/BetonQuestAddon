package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.items

import com.github.mrjimin.betonquestaddon.util.toLegacy
import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItem
import org.bukkit.inventory.ItemStack

class IaItem(
    private val stack: CustomStack
) : QuestItem {

    override fun getName(): String? {
        return stack.displayName
    }

    override fun getLore(): List<String?>? {
        val itemStack = stack.itemStack
        return itemStack.lore()?.map { it.toLegacy() }
    }

    override fun generate(stackSize: Int, profile: Profile?): ItemStack {
        val itemStack = stack.itemStack.clone()
        itemStack.amount = stackSize
        return itemStack
    }

    override fun matches(item: ItemStack?): Boolean {
        val customStack = CustomStack.byItemStack(item) ?: return false
        return customStack.namespacedID == stack.namespacedID
    }
}