package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.block

import com.github.mrjimin.betonquestaddon.compatibility.craftengine.CeParser
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.CeObjective
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument

class CeBlockPlaceObjectiveFactory(
    private val loggerFactory: BetonQuestLoggerFactory
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val itemID = instruction.get(CeParser)
        val targetAmount = instruction.getValue("amount", Argument.NUMBER_NOT_LESS_THAN_ONE, 1)!!
        val log = loggerFactory.create(CeObjective::class.java)
        return CeBlockPlace(instruction, targetAmount, log, itemID)
    }
}