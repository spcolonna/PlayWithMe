package com.lucca.domain

import com.lucca.domain.entity.Event
import com.lucca.domain.enums.EventStates

class Given {
    companion object {
        fun aEvent(
            eventId: String = "eventId",
            playerId: String = "playerId",
            subscriberId: String = "subscriberId",
            date: String = "date",
            reservationConfirm: Boolean = false,
            state: EventStates = EventStates.Created
        ) = Event(eventId, playerId, subscriberId, date, reservationConfirm, state)
    }
}