package com.github.mrjimin.betonquestaddon.compatibility.craftengine.event

import com.github.mrjimin.betonquestaddon.util.event.TargetType
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.api.quest.event.thread.PrimaryServerThreadEvent

class CraftEngineEventFactory(
    private val data: PrimaryServerThreadData,
    private val targetType: TargetType
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val itemId = instruction[Argument.STRING]
        val location = instruction[Argument.LOCATION]

        val playSound = instruction.getValue("playSound", Argument.BOOLEAN)

        return PrimaryServerThreadEvent(
            CraftEngineEvent(itemId, location, playSound, targetType),
            data
        )
    }

}