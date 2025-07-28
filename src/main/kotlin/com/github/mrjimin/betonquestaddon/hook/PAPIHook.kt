package com.github.mrjimin.betonquestaddon.hook

import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.entity.Player

object PAPIHook {

    fun String.toPAPI(player: Player) =
        PlaceholderAPI.setPlaceholders(player, this)

}