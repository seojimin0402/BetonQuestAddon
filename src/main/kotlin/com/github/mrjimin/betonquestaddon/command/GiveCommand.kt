package com.github.mrjimin.betonquestaddon.command

import com.github.mrjimin.betonquestaddon.api.BQAddonItems
import com.github.mrjimin.betonquestaddon.util.toMiniMessage
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.EntitySelectorArgument
import dev.jorel.commandapi.arguments.IntegerArgument
import dev.jorel.commandapi.arguments.TextArgument
import dev.jorel.commandapi.executors.CommandExecutor
import org.bukkit.entity.Player

class GiveCommand() {

    @Suppress("UNCHECKED_CAST")
    fun build(): CommandAPICommand {
        return CommandAPICommand("give")
            .withPermission("betonquestaddon.command.give")
            .withArguments(
                EntitySelectorArgument.ManyPlayers("targets"),
                TextArgument("item")
                    .replaceSuggestions(ArgumentSuggestions.strings { BQAddonItems.allIds().toTypedArray() }),
                IntegerArgument("amount").setOptional(true)
                )
            .executes(CommandExecutor { sender, args ->
                val targets = args[0] as Collection<Player>
                val itemID = args[1] as String
                val amount = args[2] as? Int? ?: 1

                val itemBuilder = BQAddonItems.itemFromId(itemID)
                if (itemBuilder == null) {
                    sender.sendMessage("<#888888>BetonQuestAddon</#888888> <red>Item '$itemID' not found.</red>".toMiniMessage())
                    return@CommandExecutor
                }

                val itemStack = itemBuilder.amount(amount).build()

                for (target in targets) {
                    val leftover = target.inventory.addItem(itemStack)
                    if (leftover.isNotEmpty()) {
                        for (stack in leftover.values) {
                            target.world.dropItem(target.location, stack)
                        }
                    }
                }

                if (targets.size == 1) {
                    sender.sendMessage("<#888888>BetonQuestAddon</#888888> <#00ff00>Gave $itemID<gray>x</gray>$amount to <#00ccff>${targets.first().name}</#00ccff></#00ff00>".toMiniMessage())
                } else {
                    sender.sendMessage("<#888888>BetonQuestAddon</#888888> <#00ff00>Gave $itemID<gray>x</gray>$amount to <#00ccff>${targets.size}</#00ccff> players</#00ff00>".toMiniMessage())
                }
            })
    }
}