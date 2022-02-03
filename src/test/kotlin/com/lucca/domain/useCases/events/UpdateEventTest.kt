package com.lucca.domain.useCases.events

import com.lucca.delivery.dto.UpdateEventDto
import com.lucca.domain.Given
import com.lucca.domain.doubles.EventRepositoryDouble
import com.lucca.domain.enums.EventStates
import com.lucca.domain.useCase.events.UpdateEventUseCase
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.Ignore
import org.junit.Test

class UpdateEventTest {

    @Test
    fun `cancel event by player`() {
        val eventId = "eventId"
        val repository = EventRepositoryDouble(storedEvent = listOf(Given.aEvent(eventId = eventId)))
        val updateEventDto = givenAUpdateEventDto(state = EventStates.CancelByPlayer)
        val useCase = UpdateEventUseCase(repository)
        val expected = Given.aEvent(eventId = eventId, reservationConfirm = false, state = EventStates.CancelByPlayer)

        useCase.execute(eventId, updateEventDto)

        repository.wasCalled.shouldBeTrue()
        repository.lastUpdateEvent.shouldBe(expected)
    }

    @Test
    fun `validate event id`() {
        val eventId = "validEventId"
        val repository = EventRepositoryDouble(storedEvent = listOf(Given.aEvent(eventId = eventId)))
        val useCase = UpdateEventUseCase(repository)

        val result = useCase.isContextValid(eventId, givenAUpdateEventDto())

        repository.wasCalled.shouldBeTrue()
        result.shouldBeTrue()
    }

    @Test
    fun `invalid event id`() {
        val repository = EventRepositoryDouble(storedEvent = listOf(Given.aEvent()))
        val useCase = UpdateEventUseCase(repository)

        val result = useCase.isContextValid("invalidEventId", givenAUpdateEventDto())

        repository.wasCalled.shouldBeTrue()
        result.shouldBeFalse()
    }

    @Test
    fun `validate player id`() {
        val eventId = "validEventId"
        val playerId = "validPlayerId"
        val dto = givenAUpdateEventDto(playerId = playerId)
        val repository =
            EventRepositoryDouble(storedEvent = listOf(Given.aEvent(eventId = eventId, playerId = playerId)))
        val useCase = UpdateEventUseCase(repository)

        val result = useCase.isContextValid(eventId, dto)

        repository.wasCalled.shouldBeTrue()
        result.shouldBeTrue()
    }

    @Test
    fun `invalid player id`() {
        val repository = EventRepositoryDouble(storedEvent = listOf(Given.aEvent()))
        val useCase = UpdateEventUseCase(repository)

        val result = useCase.isContextValid("eventId", givenAUpdateEventDto(playerId = "invalidPlayerId"))

        repository.wasCalled.shouldBeTrue()
        result.shouldBeFalse()
    }

    @Test
    fun `validate subscriber id`() {
        val eventId = "validEventId"
        val subscriberId = "validSubscriberId"
        val dto = givenAUpdateEventDto(subscriberId = subscriberId)
        val repository =
            EventRepositoryDouble(storedEvent = listOf(Given.aEvent(eventId = eventId, subscriberId = subscriberId)))
        val useCase = UpdateEventUseCase(repository)

        val result = useCase.isContextValid(eventId, dto)

        repository.wasCalled.shouldBeTrue()
        result.shouldBeTrue()
    }

    @Test
    fun `invalid subscriber id`() {
        val repository = EventRepositoryDouble(storedEvent = listOf(Given.aEvent()))
        val useCase = UpdateEventUseCase(repository)

        val result = useCase.isContextValid("eventId", givenAUpdateEventDto(subscriberId = "invalidSubscriberId"))

        repository.wasCalled.shouldBeTrue()
        result.shouldBeFalse()
    }

    private fun givenAUpdateEventDto(
        playerId: String = "playerId",
        subscriberId: String = "subscriberId",
        date: String = "date",
        reservationConfirm: Boolean = false,
        state: EventStates = EventStates.Created
    ) = UpdateEventDto(playerId, subscriberId, date, reservationConfirm, state)
}

