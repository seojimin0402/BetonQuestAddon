package com.github.mrjimin.betonquestaddon.compatibility.bqaddon.items

import net.kyori.adventure.text.Component
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.item.QuestItem
import org.betonquest.betonquest.item.QuestItemWrapper
import org.bukkit.Material

class BQAddonItemWrapper(
    private val type: Variable<Material>,
    private val itemName: Variable<Component>,
    private val lore: Variable<List<Component>>,
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem? {
        return BQAddonItem(
            type.getValue(profile),
            itemName.getValue(profile),
            lore.getValue(profile)
        )
    }

}