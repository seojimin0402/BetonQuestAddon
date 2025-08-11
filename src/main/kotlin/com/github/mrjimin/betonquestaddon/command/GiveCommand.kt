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
                    .replaceSuggestions(ArgumentSuggestions.strings(BQAddonItems.getIds())),
                IntegerArgument("amount")
                )
            .executes(CommandExecutor { sender, args ->
                val targets = args[0] as Collection<Player>
                val itemID = args[1] as String
                val amount = args[2] as Int

                val itemBuilder = BQAddonItems.itemFromId(itemID)
                if (itemBuilder == null) {
                    sender.sendMessage("<gray>BetonQuestAddon</gray> <red>Item '$itemID' not found.</red>".toMiniMessage())
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
                    sender.sendMessage("<gray>BetonQuestAddon</gray> <green>Gave $amount of $itemID to ${targets.first().name}.</green>".toMiniMessage())
                } else {
                    sender.sendMessage("<gray>BetonQuestAddon</gray> <green>Gave $amount of $itemID to ${targets.size} players.</green>".toMiniMessage())
                }
            })
    }
}