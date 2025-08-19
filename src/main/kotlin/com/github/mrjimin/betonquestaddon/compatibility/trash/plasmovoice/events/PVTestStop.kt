//package com.github.mrjimin.betonquestaddon.compatibility.trash.plasmovoice.events
//
//import com.github.mrjimin.betonquestaddon.hook.PVHook
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import org.betonquest.betonquest.api.profile.Profile
//import org.betonquest.betonquest.api.quest.event.PlayerEvent
//import org.betonquest.betonquest.instruction.variable.Variable
//
//class PVTestStop(
//    private val idVar: Variable<String>,
//) : PlayerEvent {
//
//    override fun execute(profile: Profile) {
//        val id = idVar.getValue(profile)
//
//        CoroutineScope(Dispatchers.Default).launch {
//            PVHook.stopTrack(id)
//        }
//    }
//}