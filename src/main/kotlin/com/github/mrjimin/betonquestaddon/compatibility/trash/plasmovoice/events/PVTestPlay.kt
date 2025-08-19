//package com.github.mrjimin.betonquestaddon.compatibility.trash.plasmovoice.events
//
//import com.github.mrjimin.betonquestaddon.compatibility.trash.plasmovoice.addon.PVAddonPlugin
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import org.betonquest.betonquest.api.profile.Profile
//import org.betonquest.betonquest.api.quest.event.PlayerEvent
//import org.betonquest.betonquest.instruction.variable.Variable
//import org.bukkit.Location
//
//class PVTestPlay(
//    private val idVar: Variable<String>,
//    private val locationVar: Variable<Location>
//) : PlayerEvent {
//
//    override fun execute(profile: Profile) {
//        val trackId = idVar.getValue(profile)
//        val loc = locationVar.getValue(profile)
//
//        PVAddonPlugin.debugLogger.log("Executing PVTestPlay: $trackId at $loc")
//
//        // Coroutine으로 안전하게 실행
//        CoroutineScope(Dispatchers.Default).launch {
//            PVHook.playTrack(trackId, loc).join()
//        }
//    }
//}
//
