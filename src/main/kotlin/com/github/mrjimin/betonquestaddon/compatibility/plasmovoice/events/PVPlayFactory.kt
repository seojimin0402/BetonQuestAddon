package com.github.mrjimin.betonquestaddon.compatibility.plasmovoice.events

import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.event.PrimaryServerThreadEvent

class PVPlayFactory(
    private val data: PrimaryServerThreadData
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val url = instruction.get(Argument.STRING)
        val loc = instruction.getValue("at", Argument.LOCATION)
        val target = instruction.getValue("target", Argument.STRING, "all")
        val id = instruction.getValue("id", Argument.STRING)
        val range = instruction.getValue("range", Argument.NUMBER, 32)
        val loop = instruction.getValue("loop", Argument.BOOLEAN, false)

        return PrimaryServerThreadEvent(
            PVPlay(url, loc, target, id, range, loop),
            data
        )
    }
}
