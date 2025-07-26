package com.github.mrjimin.betonquestaddon.compatibility.nexo

import com.github.mrjimin.betonquestaddon.util.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.checkPlugin
import com.nexomc.nexo.api.NexoItems
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.instruction.argument.Argument

object NxParser : Argument<String> {

    init {
        if (!"Nexo".checkPlugin()) throw NotFoundPlugin("Nexo")
    }

    override fun apply(value: String): String = value.trim().takeIf { NexoItems.exists(it) }
        ?: throw QuestException("Invalid or missing Nexo Item ID: '$value'")
}