package com.github.mrjimin.betonquestaddon.command

import com.github.mrjimin.betonquestaddon.gui.BQAddonItemGUI
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.PlayerCommandExecutor

class GUICommand {

    fun build(): CommandAPICommand {
        return CommandAPICommand("inventory")
            .withAliases("inv")
            .withPermission("betonquestaddon.command.inventory")
            .executesPlayer(PlayerCommandExecutor {player , _ ->
                BQAddonItemGUI.openMainGUI(player)
            })
    }
}