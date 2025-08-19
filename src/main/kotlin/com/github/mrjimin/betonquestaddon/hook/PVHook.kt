//package com.github.mrjimin.betonquestaddon.hook
//
//import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
//import com.github.mrjimin.betonquestaddon.compatibility.trash.plasmovoice.addon.PVAddonPlugin
//import com.github.mrjimin.betonquestaddon.compatibility.trash.plasmovoice.addon.TrackRegistry
//import kotlinx.coroutines.*
//import kotlinx.coroutines.future.await
//import org.bukkit.Location
//import su.plo.slib.api.server.position.ServerPos3d
//
//object PVHook {
//
//    private val plugin = BetonQuestAddonPlugin.instance
//    private val audioPlayerManager = PVAddonPlugin.audioPlayerManager
//    private val debugLogger = PVAddonPlugin.debugLogger
//    private val config = PVAddonPlugin.configsManager.addonConfig
//    private val voiceServer = PVAddonPlugin.voiceServer
//
//    private val activeTracks = mutableMapOf<String, CoroutineScope>()
//
//    fun playTrack(trackId: String, location: Location): Job {
//        val scope = CoroutineScope(Dispatchers.Default)
//        activeTracks[trackId] = scope
//
//        return scope.launch {
//            val trackData = TrackRegistry.track(trackId) ?: return@launch
//            val track = try { audioPlayerManager.getTrack(trackData.url).await() } catch(e: Exception){ return@launch }
//
//            val world = voiceServer.minecraftServer.getWorld(location.world)
//            val pos = ServerPos3d(world, location.x + 0.5, location.y + 1.5, location.z + 0.5)
//            val source = PVAddonPlugin.sourceLine.createStaticSource(pos, false)
//            val distance = config.distance
//
//            debugLogger.log("Playing track \"$trackId\" at $location with distance $distance")
//
//            val trackJob = audioPlayerManager.startTrackJob(track, source, distance)
//
//            try {
//                if (trackJob is Job) trackJob.join()
//            } finally {
//                withContext(NonCancellable) {
//                    debugLogger.log("Track \"$trackId\" ended at $location")
//                    source.remove()
//                    activeTracks.remove(trackId)
//                }
//            }
//        }
//    }
//
//    suspend fun stopTrack(trackId: String) {
//        val scope = activeTracks.remove(trackId) ?: return
//        withContext(NonCancellable) {
//            debugLogger.log("Stopping track \"$trackId\" manually")
//            scope.cancel()
//        }
//    }
//}
