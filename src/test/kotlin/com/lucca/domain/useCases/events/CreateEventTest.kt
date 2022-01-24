package com.lucca.domain.useCases.events

import com.lucca.delivery.dto.CreateEventDto
import com.lucca.domain.doubles.EventRepositoryDouble
import com.lucca.domain.doubles.IdGeneratorDouble
import com.lucca.domain.entity.CreateEvent
import com.lucca.domain.useCase.events.CreateEventUseCase
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.Test

class CreateEventTest {

    @Test
    fun `create event`() {
        val eventId = "eventId"
        val reservationConfirm = false
        val idGenerator = IdGeneratorDouble(eventId)
        val repository = EventRepositoryDouble()
        val dto = givenACreateEventDto()
        val expected = givenACreateEvent(eventId = eventId, reservationConfirm = reservationConfirm)
        val useCase = CreateEventUseCase(idGenerator, repository)

        val result = useCase.execute(dto)

        result.shouldBe(eventId)
        repository.wasCalled.shouldBeTrue()
        repository.lastCreateEvent.shouldBe(expected)
    }

    private fun givenACreateEvent(
        eventId: String = "eventId",
        playerId: String = "playerId",
        subscriberId: String = "subscriberId",
        date: String = "date",
        reservationConfirm: Boolean = false
    ): CreateEvent {
        return CreateEvent(eventId, playerId,subscriberId,date,reservationConfirm)
    }

    private fun givenACreateEventDto(
        playerId: String = "playerId",
        subscriberId: String = "subscriberId",
        date: String = "date"
    ): CreateEventDto {
        return CreateEventDto(playerId, subscriberId, date)
    }
}

