package com.lucca.domain.interace

import com.lucca.delivery.dto.UpdateEventDto
import com.lucca.domain.entity.Event

interface IEventRepository {
    fun store(event: Event)
    fun update(event: Event)
    fun has(eventId: String): Boolean
}