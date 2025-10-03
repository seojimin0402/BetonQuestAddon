package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.items

import dev.lone.itemsadder.api.CustomStack
import net.kyori.adventure.text.Component
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItem
import org.bukkit.inventory.ItemStack

class IaItem(
    private val stack: CustomStack
) : QuestItem {

    override fun getName(): Component {
        return stack.itemStack.displayName()
    }

    override fun getLore(): List<Component?>? {
        val itemStack = stack.itemStack
        return itemStack.lore()?.map { it }
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