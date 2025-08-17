package com.github.mrjimin.betonquestaddon.compatibility.plasmovoice.events

import com.github.mrjimin.betonquestaddon.hook.PVHook
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.instruction.variable.Variable

class PVStop(
    private val idVar: Variable<String>,
    private val targetVar: Variable<String?>?,
) : PlayerEvent {

    override fun execute(profile: Profile) {
        val id = idVar.getValue(profile)
        val target = targetVar?.getValue(profile)

        if (target == null || target.equals("all", true)) {
            PVHook.stopTrackAll(id)
        } else {
            val player = org.bukkit.Bukkit.getPlayerExact(target) ?: return
            PVHook.stopTrack(player, id)
        }
    }
}
