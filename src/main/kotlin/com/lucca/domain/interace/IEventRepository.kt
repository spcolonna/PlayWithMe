package com.lucca.domain.interace

import com.lucca.domain.entity.Event

interface IEventRepository {
    fun store(event: Event)
    fun has(eventId: String): Boolean
    fun update(event: Event)

}