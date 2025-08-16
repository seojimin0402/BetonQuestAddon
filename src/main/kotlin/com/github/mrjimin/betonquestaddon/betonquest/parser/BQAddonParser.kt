package com.github.mrjimin.betonquestaddon.betonquest.parser

import com.github.mrjimin.betonquestaddon.api.BQAddonItems
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.instruction.argument.Argument

object BQAddonParser : Argument<String> {

    override fun apply(value: String): String = value.trim().takeIf { BQAddonItems.exists(it) }
        ?: throw QuestException("Invalid or missing BetonQuestAddon Item ID: '$value'")
}