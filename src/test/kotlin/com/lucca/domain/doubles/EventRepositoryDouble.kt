package com.lucca.domain.doubles

import com.lucca.delivery.dto.UpdateEventDto
import com.lucca.domain.entity.Event
import com.lucca.domain.interace.IEventRepository

class EventRepositoryDouble(
    private var storedEvent: List<Event> = listOf()
) : IEventRepository {
    lateinit var lastUpdateEvent: Event
    lateinit var lastCreateEvent: Event

    var wasCalled: Boolean = false

    override fun store(event: Event) {
        wasCalled = true
        lastCreateEvent = event
    }

    override fun update(event: Event) {
        wasCalled = true
        lastUpdateEvent = event
    }

    override fun has(eventId: String): Boolean {
        wasCalled = true
        return storedEvent.any { it.eventId == eventId }
    }
}