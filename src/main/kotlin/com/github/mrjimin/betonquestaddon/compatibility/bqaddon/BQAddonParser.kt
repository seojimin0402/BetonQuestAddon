package com.github.mrjimin.betonquestaddon.compatibility.bqaddon

import com.github.mrjimin.betonquestaddon.util.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.checkPlugin
import com.nexomc.nexo.api.NexoItems
import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.instruction.argument.Argument

object BQAddonParser : Argument<String> {

    override fun apply(value: String): String = value.trim().takeIf { NexoItems.exists(it) }
        ?: throw QuestException("Invalid or missing Nexo Item ID: '$value'")
}