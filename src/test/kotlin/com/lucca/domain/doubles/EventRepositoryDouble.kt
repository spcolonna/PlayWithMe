package com.lucca.domain.doubles

import com.lucca.domain.entity.CreateEvent
import com.lucca.domain.interace.IEventRepository

class EventRepositoryDouble : IEventRepository {
    lateinit var lastCreateEvent: CreateEvent

    var wasCalled: Boolean = false

    override fun store(createEvent: CreateEvent) {
        wasCalled = true
        lastCreateEvent = createEvent
    }
}