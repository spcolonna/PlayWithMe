package com.lucca.domain.useCase.subscriber

import com.lucca.delivery.dto.CreateSubscriberDto
import com.lucca.domain.Builder
import com.lucca.domain.interace.IIdGenerator
import com.lucca.domain.interace.ISubscriberRepository

class CreateSubscriberUseCase(private val idGenerator: IIdGenerator, private val repository: ISubscriberRepository) {
    fun execute(dto: CreateSubscriberDto): String {
        val id = idGenerator.getId()
        repository.store(createSubscriber(id, dto))
        return id
    }

    fun isContextValid(dto: CreateSubscriberDto) = repository.isMailAvailable(dto.mail)

    private fun createSubscriber(id: String, dto: CreateSubscriberDto) =
        Builder.createSubscriberFromDto(id, dto)
}

