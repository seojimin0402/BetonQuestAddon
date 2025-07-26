package com.github.mrjimin.betonquestaddon.compatibility.headdatabase

import com.github.mrjimin.betonquestaddon.util.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.checkPlugin
import me.arcaniax.hdb.api.HeadDatabaseAPI
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.instruction.argument.Argument

object HDBParser : Argument<String> {

    init {
        if (!"HeadDatabase".checkPlugin()) throw NotFoundPlugin("HeadDatabase")
    }

    override fun apply(value: String): String = value.trim().takeIf { HeadDatabaseAPI().isHead(it) }
        ?: throw QuestException("Invalid or missing HeadDatabase Item ID: '$value'")
}