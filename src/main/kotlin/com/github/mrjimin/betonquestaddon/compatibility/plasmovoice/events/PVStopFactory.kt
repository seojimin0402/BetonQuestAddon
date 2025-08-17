package com.github.mrjimin.betonquestaddon.compatibility.plasmovoice.events

import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.event.PrimaryServerThreadEvent

class PVStopFactory(
    private val data: PrimaryServerThreadData
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val id = instruction.get(Argument.STRING)
        val target = instruction.getValue("target", Argument.STRING, "all")
        return PrimaryServerThreadEvent(PVStop(id, target), data)
    }
}
