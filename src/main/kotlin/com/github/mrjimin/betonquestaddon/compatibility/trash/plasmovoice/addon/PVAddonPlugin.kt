//package com.github.mrjimin.betonquestaddon.compatibility.trash.plasmovoice.addon
//
//import com.github.mrjimin.betonquestaddon.BuildConstants
//import org.bukkit.plugin.java.JavaPlugin
//import su.plo.slib.api.logging.McLoggerFactory
//import su.plo.voice.api.addon.AddonLoaderScope
//import su.plo.voice.api.addon.InjectPlasmoVoice
//import su.plo.voice.api.addon.annotation.Addon
//import su.plo.voice.api.addon.annotation.Dependency
//import su.plo.voice.api.logging.DebugLogger
//import su.plo.voice.api.server.PlasmoVoiceServer
//import su.plo.voice.api.server.audio.line.ServerSourceLine
//import su.plo.voice.api.server.audio.source.ServerProximitySource
//import su.plo.voice.discs.PlasmoAudioPlayerManager
//
//@Addon(
//    id = "pv-addon-betonquestaddon",
//    scope = AddonLoaderScope.SERVER,
//    version = BuildConstants.VERSION,
//    authors = ["MrJimin"],
//    dependencies = [Dependency(id = "pv-addon-lavaplayer-lib")]
//)
//class PVAddonPlugin(val plugin: JavaPlugin) {
//
//    @InjectPlasmoVoice
//    lateinit var voiceServer: PlasmoVoiceServer
//
//    lateinit var sourceLine: ServerSourceLine private set
//    private lateinit var configsManager: PVConfigsManager
//    lateinit var debugLogger: DebugLogger
//    lateinit var audioPlayerManager: PlasmoAudioPlayerManager
//
//    fun load() {
//        instance = this
//        initialized = true
//        PlasmoVoiceServer.getAddonsLoader().load(this)
//    }
//
//    fun onEnableAddon() {
//        loadConfig()
//        audioPlayerManager = PlasmoAudioPlayerManager()
//        audioPlayerManager.registerSources()
//        // PVHook.initialize(audioPlayerManager, voiceServer, configsManager)
//        plugin.logger.info("PVAddonPlugin enabled (PlasmoVoice ready).")
//    }
//
//    fun unload() {
//        audioPlayerManager.shutdown()
//        PlasmoVoiceServer.getAddonsLoader().unload(this)
//    }
//
//    fun loadConfig() {
//        configsManager = PVConfigsManager(plugin)
//        configsManager.reload(voiceServer)
//
//        debugLogger = DebugLogger(McLoggerFactory.createLogger(plugin.slF4JLogger.name))
//        debugLogger.enabled(voiceServer.debugLogger.enabled())
//
//        voiceServer.sourceLineManager.unregister(addonName)
//
//        sourceLine = this::class.java.classLoader.getResourceAsStream("pv-addon-bqa/speaker_dqa.png")!!.use { icon ->
//            voiceServer.sourceLineManager.createBuilder(
//                this,
//                addonName,
//                "pv.activation.$addonName",
//                icon,
//                configsManager.addonConfig.sourceLineWeight
//            ).apply {
//                setDefaultVolume(configsManager.addonConfig.defaultSourceLineVolume)
//            }.build()
//        }
//    }
//
//    companion object {
//        lateinit var instance: PVAddonPlugin private set
//        val sourceLine: ServerSourceLine get() = instance.sourceLine
//        val voiceServer: PlasmoVoiceServer get() = instance.voiceServer
//        val debugLogger: DebugLogger get() = instance.debugLogger
//        val audioPlayerManager: PlasmoAudioPlayerManager get() = instance.audioPlayerManager
//        val configsManager: PVConfigsManager get() = instance.configsManager
//        private var initialized = false
//        val isInitialized: Boolean get() = initialized
//        const val addonName = "betonquestaddon"
//    }
//
//    object PVVoiceUtil {
//        fun getSourceLine(): ServerSourceLine = sourceLine
//
//        fun getPlayerSource(playerName: String): ServerProximitySource<*> {
//            val voicePlayer = voiceServer.playerManager
//                .getPlayerByName(playerName)
//                .orElseThrow { IllegalStateException("player $playerName not found") }
//
//            val source = getSourceLine().createPlayerSource(voicePlayer, false)
//            if (source.filters.isNotEmpty()) source.removeFilter(source.filters.first())
//            return source
//        }
//    }
//}
