package com.lucca.domain.doubles

import com.lucca.domain.Given
import com.lucca.domain.entity.Event
import com.lucca.domain.interace.IEventRepository

class EventRepositoryDouble : IEventRepository {
    lateinit var lastUpdateEvent: Event
    lateinit var lastGetEventCalled: String
    lateinit var lastCreateEvent: Event

    var storeWasCalled: Boolean = false
    var getWasCalled: Boolean = false

    override fun store(createEvent: Event) {
        storeWasCalled = true
        lastCreateEvent = createEvent
    }

    override fun get(eventId: String): Event {
        getWasCalled = true
        return Given.aEvent()
    }
}