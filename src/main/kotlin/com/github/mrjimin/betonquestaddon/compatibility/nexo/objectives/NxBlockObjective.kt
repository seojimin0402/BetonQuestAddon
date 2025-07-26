package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives

import com.github.mrjimin.betonquestaddon.compatibility.AbstractObjective
import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable

open class NxBlockObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    langMessageKey: LangMessageKey,
    log: BetonQuestLogger,
    itemID: Variable<String>
) : AbstractObjective<String>(instruction, targetAmount, langMessageKey, log, itemID) {
    override fun matches(expected: String, inputId: String?): Boolean {
        return expected == inputId
    }
}