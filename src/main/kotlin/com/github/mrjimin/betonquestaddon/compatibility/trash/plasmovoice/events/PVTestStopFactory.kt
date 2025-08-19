//package com.github.mrjimin.betonquestaddon.compatibility.trash.plasmovoice.events
//
//import org.betonquest.betonquest.api.quest.event.PlayerEvent
//import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
//import org.betonquest.betonquest.instruction.Instruction
//import org.betonquest.betonquest.instruction.argument.Argument
//import org.betonquest.betonquest.quest.PrimaryServerThreadData
//import org.betonquest.betonquest.quest.event.PrimaryServerThreadEvent
//
//class PVTestStopFactory(
//    private val data: PrimaryServerThreadData
//) : PlayerEventFactory {
//
//    override fun parsePlayer(instruction: Instruction): PlayerEvent {
//        val id = instruction.get(Argument.STRING)
//        return PrimaryServerThreadEvent(PVTestStop(id), data)
//    }
//}