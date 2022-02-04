package com.lucca.domain.useCase.events

import com.lucca.delivery.dto.UpdateEventDto
import com.lucca.domain.Builder
import com.lucca.domain.interace.IEventRepository
import com.lucca.domain.interace.IPlayerRepository
import com.lucca.domain.interace.ISubscriberRepository

class UpdateEventUseCase(
    private val eventRepository: IEventRepository,
    private val playerRepository: IPlayerRepository,
    private val subscriberRepository: ISubscriberRepository
) {
    fun execute(eventId: String, dto: UpdateEventDto) =
        eventRepository.update(Builder.createEventFromUpdateDto(eventId, dto))

    fun isContextValid(eventId: String, dto: UpdateEventDto) =
        eventRepository.has(eventId) && playerRepository.has(dto.playerId) && subscriberRepository.has(dto.subscriberId)
}