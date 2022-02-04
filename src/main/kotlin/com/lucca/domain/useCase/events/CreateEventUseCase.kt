package com.lucca.domain.useCase.events

import com.lucca.delivery.dto.CreateEventDto
import com.lucca.domain.Builder
import com.lucca.domain.interace.IEventRepository
import com.lucca.domain.interace.IIdGenerator
import com.lucca.domain.interace.IPlayerRepository
import com.lucca.domain.interace.ISubscriberRepository

class CreateEventUseCase(
    private val idGenerator: IIdGenerator,
    private val repository: IEventRepository,
    private val playerRepository: IPlayerRepository,
    private val subscriberRepository: ISubscriberRepository
) {
    fun execute(dto: CreateEventDto): String {
        val id = idGenerator.getId()
        repository.store(Builder.createEventFromCreateDto(id, dto))
        return id
    }

    fun isContextValid(dto: CreateEventDto) =
        playerRepository.has(dto.playerId) && subscriberRepository.has(dto.subscriberId)

}