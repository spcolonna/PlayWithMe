package com.lucca.domain

import com.lucca.delivery.dto.*
import com.lucca.domain.entity.Event
import com.lucca.domain.entity.CreatePlayer
import com.lucca.domain.entity.CreateSubscriber
import com.lucca.domain.entity.Scheduler
import com.lucca.domain.enums.EventStates

class Builder {
    companion object {
        fun createSubscriberFromDto(id: String, dto: CreateSubscriberDto) =
            CreateSubscriber(id, dto.mail, dto.password, dto.name, dto.birthDate)

        fun createPlayerFromDto(id: String, dto: CreatePlayerDto): CreatePlayer =
            CreatePlayer(id, dto.mail, dto.password, dto.name, dto.accountNumber, listOf())

        fun createEventFromCreateDto(id: String, dto: CreateEventDto): Event =
            Event(id, dto.playerId, dto.subscriberId, dto.date, false, EventStates.Created)

        fun createEventFromUpdateDto(eventId: String, dto: UpdateEventDto) =
            Event(eventId, dto.playerId, dto.subscriberId, dto.date, dto.reservationConfirm, dto.state)

        fun createSchedulerFromDto(schedulerId: String, dto: CreateSchedulerDto) =
            Scheduler(schedulerId, dto.playerId, dto.availableDays, dto.availableHours)
    }
}