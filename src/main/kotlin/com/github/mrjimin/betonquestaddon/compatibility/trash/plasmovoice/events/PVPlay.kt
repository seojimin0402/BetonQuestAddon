//package com.github.mrjimin.betonquestaddon.compatibility.plasmovoice.events
//
//import com.github.mrjimin.betonquestaddon.hook.PVHook
//import org.betonquest.betonquest.api.profile.Profile
//import org.betonquest.betonquest.api.quest.event.PlayerEvent
//import org.betonquest.betonquest.instruction.variable.Variable
//import org.bukkit.Location
//
//class PVPlay(
//    private val urlVar: Variable<String>,
//    private val locVar: Variable<Location?>?,
//    private val targetVar: Variable<String?>?,
//    private val idVar: Variable<String?>?,
//    private val rangeVar: Variable<Number?>?,
//    private val loopVar: Variable<Boolean?>?
//) : PlayerEvent {
//
//    override fun execute(profile: Profile) {
//        val url = urlVar.getValue(profile) ?: return
//        val loc = locVar?.getValue(profile)
//        val target = targetVar?.getValue(profile)
//        val id = idVar?.getValue(profile)?.replace(Regex("[^A-Za-z0-9_-]"), "_") ?: url.hashCode().toString()
//        val range = rangeVar?.getValue(profile)?.toShort() ?: 16
//        val loop = loopVar?.getValue(profile) ?: false
//
//        if (loc != null) {
//            PVHook.playTrackAtLocation(loc, id, url, range, loop)
//            return
//        }
//
//        val players = if (target == null || target.equals("all", true)) {
//            org.bukkit.Bukkit.getOnlinePlayers()
//        } else {
//            listOf(org.bukkit.Bukkit.getPlayerExact(target) ?: return)
//        }
//
//        players.forEach { PVHook.playTrack(it, id, url, range, loop) }
//    }
//}
