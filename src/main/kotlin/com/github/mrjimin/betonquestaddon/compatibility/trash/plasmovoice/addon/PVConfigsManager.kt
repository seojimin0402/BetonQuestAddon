//package com.github.mrjimin.betonquestaddon.compatibility.trash.plasmovoice.addon
//
//import com.github.mrjimin.betonquestaddon.util.loadYamlFile
//import org.bukkit.configuration.file.YamlConfiguration
//import org.bukkit.plugin.java.JavaPlugin
//import su.plo.config.Config
//import su.plo.config.ConfigField
//import su.plo.config.provider.ConfigurationProvider
//import su.plo.config.provider.toml.TomlConfiguration
//import su.plo.slib.api.server.McServerLib
//import su.plo.voice.api.server.PlasmoVoiceServer
//import java.io.File
//import java.io.IOException
//import java.io.InputStream
//import java.net.URI
//
//class PVConfigsManager(val plugin: JavaPlugin) {
//    private val rawTrackFile = File(plugin.dataFolder, "pv-addon-bqa/track.yml")
//
//    var trackFile: YamlConfiguration = rawTrackFile.loadYamlFile()
//        private set
//
//    lateinit var addonConfig: PVAddonConfig
//        private set
//
//    fun reload(server: PlasmoVoiceServer) {
//        addonConfig = PVAddonConfig.loadConfig(server)
//        trackFile = rawTrackFile.loadYamlFile()
//        TrackRegistry.loadFromYaml(trackFile)
//    }
//
//}
//
//@Config
//class PVAddonConfig {
//
//    @ConfigField
//    var sourceLineWeight = 10
//
//    @ConfigField(
//        comment = """
//            The default volume. Volume is configured on the client side
//            and can be adjusted via the mod settings.
//        """
//    )
//    var defaultSourceLineVolume = 0.5
//
//    @ConfigField(
//        comment = """
//                Goat horn distance.
//            """
//    )
//    val distance: Short = 15
//
//    companion object {
//        fun loadConfig(server: PlasmoVoiceServer): PVAddonConfig {
//            val addonFolder = getAddonFolder(server.minecraftServer)
//
//            server.languages.register(
//                URI.create("https://github.com/plasmoapp/plasmo-voice-crowdin/archive/refs/heads/addons.zip").toURL(),
//                "server/config.toml",
//                { resourcePath: String -> getLanguageResource(resourcePath)
//                    ?: throw Exception("Can't load language resource") },
//                File(addonFolder, "pv-addon-bqa/languages")
//            )
//            val configFile = File(addonFolder, "pv-addon-bqa/config.toml")
//
//            return toml.load<PVAddonConfig>(PVAddonConfig::class.java, configFile, false)
//                .also { toml.save(PVAddonConfig::class.java, it, configFile) }
//        }
//
//        @Throws(IOException::class)
//        private fun getLanguageResource(resourcePath: String): InputStream? =
//            javaClass.classLoader.getResourceAsStream(String.format("pv-addon-bqa/%s", resourcePath))
//
//        private val toml = ConfigurationProvider.getProvider<ConfigurationProvider>(
//            TomlConfiguration::class.java
//        )
//
//        private fun getAddonFolder(minecraftServer: McServerLib): File =
//            File(minecraftServer.configsFolder, "BetonQuestAddon")
//    }
//}