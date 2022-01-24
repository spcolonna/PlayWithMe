package com.lucca.domain

import com.lucca.delivery.dto.CreateEventDto
import com.lucca.delivery.dto.CreatePlayerDto
import com.lucca.delivery.dto.CreateSubscriberDto
import com.lucca.domain.entity.CreateEvent
import com.lucca.domain.entity.CreatePlayer
import com.lucca.domain.entity.CreateSubscriber

class Builder {
    companion object {
        fun createSubscriberFromDto(id: String, dto: CreateSubscriberDto) =
            CreateSubscriber(id, dto.mail, dto.password, dto.name, dto.birthDate)

        fun createPlayerFromDto(id: String, dto: CreatePlayerDto): CreatePlayer =
            CreatePlayer(id, dto.mail, dto.password, dto.name, dto.accountNumber, listOf())

        fun createEventFromDto(id: String, dto: CreateEventDto): CreateEvent =
            CreateEvent(id, dto.playerId, dto.subscriberId, dto.date, false)
    }
}

