package com.github.mrjimin.betonquestaddon.compatibility.craftengine

import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook
import com.github.mrjimin.betonquestaddon.util.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.checkPlugin
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.instruction.argument.Argument

object CeParser : Argument<String> {

    init {
        if (!"CraftEngine".checkPlugin()) throw NotFoundPlugin("CraftEngine")
    }

    override fun apply(value: String): String = value.trim().takeIf { CraftEngineHook.exists(it) }
        ?: throw QuestException("Invalid or missing CraftEngine Item ID: '$value'")
}