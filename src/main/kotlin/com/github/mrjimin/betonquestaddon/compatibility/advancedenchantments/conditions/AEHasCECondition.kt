package com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.conditions

import com.github.mrjimin.betonquestaddon.hook.PAPIHook.toPAPI
import com.github.mrjimin.betonquestaddon.util.compareWith
import com.github.mrjimin.betonquestaddon.util.toOnlinePlayer
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.instruction.variable.Variable

class AEHasCECondition(
    private val group: Variable<String>,
    private val operator: Variable<String>,
    private val amount: Variable<Number>
) : PlayerCondition {

    override fun check(profile: Profile): Boolean {
        val groupV = group.getValue(profile).lowercase()
        val op = operator.getValue(profile)
        val requiredAmount = amount.getValue(profile).toInt()
        val player = profile.player.toOnlinePlayer()

        val placeholder = if (groupV == "*") {
            "%advancedenchantments_item_enchants_total%"
        } else {
            "%advancedenchantments_item_enchants_total_$groupV%"
        }

        val papiValue = placeholder.toPAPI(player).toIntOrNull() ?: return false

        return papiValue.compareWith(op, requiredAmount)
    }
}