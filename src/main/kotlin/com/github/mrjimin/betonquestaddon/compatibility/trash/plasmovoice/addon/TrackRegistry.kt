//package com.github.mrjimin.betonquestaddon.compatibility.trash.plasmovoice.addon
//
//import org.bukkit.configuration.file.YamlConfiguration
//
//object TrackRegistry {
//    private val trackMap = mutableMapOf<String, TrackData>()
//
//    fun loadFromYaml(yaml: YamlConfiguration) {
//        trackMap.clear()
//        yaml.getKeys(false).forEach { key ->
//            yaml.getString(key)?.let { url ->
//                trackMap[key] = TrackData(url)
//            }
//        }
//    }
//
//    fun track(id: String): TrackData? = trackMap[id]
//
//    fun allTracks(): Map<String, TrackData> = trackMap.toMap()
//
//    fun allTrackIds(): List<String> = trackMap.keys.asSequence().sorted().toList()
//
//    fun exists(id: String): Boolean = track(id) != null
//}