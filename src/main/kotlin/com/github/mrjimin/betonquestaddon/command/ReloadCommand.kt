package com.github.mrjimin.betonquestaddon.command

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import com.github.mrjimin.betonquestaddon.config.ConfigsManager
import com.github.mrjimin.betonquestaddon.util.toMiniMessage
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.CommandExecutor

class ReloadCommand(private val plugin: BetonQuestAddonPlugin) {

    fun build(): CommandAPICommand {
        return CommandAPICommand("reload")
            .withAliases("rl")
            .withPermission("betonquestaddon.command.reload")
            .executes(CommandExecutor { sender, _ ->
                ConfigsManager(plugin).reload()
                sender.sendMessage("<gray>BetonQuestAddon</gray> <green>Reloaded successfully!".toMiniMessage())
            })
    }
}
