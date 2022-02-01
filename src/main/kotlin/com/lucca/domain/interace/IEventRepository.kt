package com.lucca.domain.interace

import com.lucca.domain.entity.Event

interface IEventRepository {
    fun store(createEvent: Event)
    fun get(eventId: String): Event

}