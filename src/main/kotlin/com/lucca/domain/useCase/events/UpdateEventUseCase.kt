package com.lucca.domain.useCase.events

import com.lucca.delivery.dto.UpdateEventDto
import com.lucca.domain.Builder
import com.lucca.domain.interace.IEventRepository

class UpdateEventUseCase(private val repository: IEventRepository) {
    fun execute(eventId: String, dto: UpdateEventDto) =
        repository.update(Builder.createEventFromUpdateDto(eventId, dto))

    fun isContextValid(eventId: String, dto: UpdateEventDto) =
        repository.has(Builder.createEventFromUpdateDto(eventId, dto))
}