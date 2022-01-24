package com.lucca.domain.useCase.events

import com.lucca.delivery.dto.CreateEventDto
import com.lucca.domain.Builder
import com.lucca.domain.interace.IEventRepository
import com.lucca.domain.interace.IIdGenerator

class CreateEventUseCase(val idGenerator: IIdGenerator, val repository: IEventRepository) {
    fun execute(dto: CreateEventDto): String {
        repository.store(Builder.createEventFromDto(idGenerator.getId(), dto))
        return "eventId"
    }

}