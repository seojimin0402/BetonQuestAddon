package com.github.mrjimin.betonquestaddon.item

import org.bukkit.Tag
import org.bukkit.damage.DamageType
import org.bukkit.tag.DamageTypeTags

object DamageTypeTagRegistry {
    val tags: Map<String, Tag<DamageType>> = DamageTypeTags::class.java.fields
        .mapNotNull { field ->
            field.get(null)?.let { field.name.uppercase() to it as Tag<DamageType> }
        }.toMap()
}
