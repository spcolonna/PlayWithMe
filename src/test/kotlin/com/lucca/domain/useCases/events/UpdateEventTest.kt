package com.lucca.domain.useCases.events

import com.lucca.delivery.dto.UpdateEventDto
import com.lucca.domain.Given
import com.lucca.domain.doubles.EventRepositoryDouble
import com.lucca.domain.enums.EventStates
import com.lucca.domain.useCase.events.UpdateEventUseCase
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.Test

class UpdateEventTest {

    @Test
    fun `cancel event by player`() {
        val eventId = "eventId"
        val repository = EventRepositoryDouble()
        val updateEventDto = givenAUpdateEventDto(state = EventStates.CancelByPlayer)
        val useCase = UpdateEventUseCase(repository)
        val expected = Given.aEvent(eventId = eventId,  reservationConfirm = false, state = EventStates.CancelByPlayer)

        useCase.execute(eventId, updateEventDto)

        repository.wasCalled.shouldBeTrue()
        repository.lastUpdateEvent.shouldBe(expected)
    }
    //TODO cancel by subscriber, confirm by player

    @Test
    fun `validate event id`(){
        val eventId = "validEventId"
        val repository = EventRepositoryDouble(storedIds = listOf(eventId))
        val useCase = UpdateEventUseCase(repository)

        val result = useCase.isContextValid(eventId)

        repository.wasCalled.shouldBeTrue()
        result.shouldBeTrue()
    }

    @Test
    fun `invalid event id`(){
        val repository = EventRepositoryDouble(storedIds = listOf("eventId"))
        val useCase = UpdateEventUseCase(repository)

        val result = useCase.isContextValid("invalidEventId")

        repository.wasCalled.shouldBeTrue()
        result.shouldBeFalse()
    }

    //TODO validate player id, validate subscriber id

    private fun givenAUpdateEventDto(
        playerId: String = "playerId",
        subscriberId: String = "subscriberId",
        date: String = "date",
        reservationConfirm: Boolean = false,
        state: EventStates = EventStates.Created
    ) = UpdateEventDto(playerId, subscriberId, date, reservationConfirm, state)
}

