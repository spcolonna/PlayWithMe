package com.lucca.domain.doubles

import com.lucca.domain.entity.Event
import com.lucca.domain.interace.IEventRepository

class EventRepositoryDouble(private var storedIds: List<String> = listOf()) : IEventRepository {
    lateinit var lastUpdateEvent: Event
    lateinit var lastCreateEvent: Event

    var wasCalled: Boolean = false

    override fun store(event: Event) {
        wasCalled = true
        lastCreateEvent = event
    }

    override fun has(eventId: String): Boolean {
        wasCalled = true
        return storedIds.any { it == eventId }
    }

    override fun update(event: Event) {
        wasCalled = true
        lastUpdateEvent = event
    }
}