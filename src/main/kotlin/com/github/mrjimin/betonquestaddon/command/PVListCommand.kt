//package com.github.mrjimin.betonquestaddon.command
//
//import com.github.mrjimin.betonquestaddon.compatibility.trash.plasmovoice.addon.TrackRegistry
//import com.github.mrjimin.betonquestaddon.util.toMiniMessage
//import dev.jorel.commandapi.CommandAPICommand
//import dev.jorel.commandapi.executors.CommandExecutor
//
//class PVListCommand {
//
//    fun build(): CommandAPICommand {
//        return CommandAPICommand("pvList")
//            .withPermission("betonquestaddon.command.pv.list")
//            .executes(CommandExecutor { sender, _ ->
//                val tracks = TrackRegistry.allTracks()
//                if (tracks.isEmpty()) {
//                    sender.sendMessage("<#888888>BetonQuestAddon</#888888> <red>No tracks available.</red>".toMiniMessage())
//                } else {
//                    sender.sendMessage("<#888888>BetonQuestAddon</#888888> <green>PlasmoVoice List:</green>".toMiniMessage())
//                    tracks.forEach { (id, url) ->
//                        sender.sendMessage("<blue>$id</blue> <gray>-</gray> <white>${url.url}</white>".toMiniMessage())
//                    }
//                }
//            })
//    }
//
//}