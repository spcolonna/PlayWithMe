package com.lucca.domain.useCases.events

import com.lucca.domain.Given
import com.lucca.domain.doubles.EventRepositoryDouble
import com.lucca.domain.enums.EventStates
import com.lucca.domain.interace.IEventRepository
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.Test

class UpdateEventTest {

    @Test
    fun `cancel event by player`() {
        val eventId = "eventId"
        val repository = EventRepositoryDouble()
        val newEventDto = givenAUpdateEventDto()
        val useCase = UpdateEventUseCase(repository)
        val expected = Given.aEvent(reservationConfirm = false, state = EventStates.CancelByPlayer)

        useCase.execute(newEventDto)

        repository.getWasCalled.shouldBeTrue()
        repository.lastGetEventCalled.shouldBe(eventId)
        repository.lastUpdateEvent.shouldBe(expected)
    }

    private fun givenAUpdateEventDto(
        eventId: String = "eventId",
        playerId: String = "playerId",
        subscriberId: String = "subscriberId",
        date: String = "date",
        reservationConfirm: Boolean = false,
        state: EventStates = EventStates.Created
    ) = UpdateEventDto(eventId, playerId, subscriberId, date, reservationConfirm, state)
}

class UpdateEventUseCase(private val repository: IEventRepository) {
    fun execute(dto: UpdateEventDto) {
        val event = repository.get(dto.eventId)
    }


}

class UpdateEventDto(
    val eventId: String,
    val playerId: String,
    val subscriberId: String,
    val date: String,
    val reservationConfirm: Boolean,
    val state: EventStates
)
