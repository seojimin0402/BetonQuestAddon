package com.github.mrjimin.betonquestaddon.util

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

fun File.loadYamlFile(): YamlConfiguration {
    parentFile?.takeIf { !it.exists() }?.mkdirs()
    takeIf { !exists() }?.createNewFile()
    return YamlConfiguration.loadConfiguration(this)
}