package com.lucca.domain.useCases.events

import com.lucca.delivery.dto.UpdateEventDto
import com.lucca.domain.Given
import com.lucca.domain.doubles.EventRepositoryDouble
import com.lucca.domain.doubles.PlayerRepositoryDouble
import com.lucca.domain.doubles.SubscriberRepositoryDouble
import com.lucca.domain.entity.CreatePlayer
import com.lucca.domain.entity.CreateSubscriber
import com.lucca.domain.entity.PlayEvent
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
        val repository = EventRepositoryDouble(storedEvent = listOf(Given.aEvent(eventId = eventId)))
        val updateEventDto = givenAUpdateEventDto(state = EventStates.CancelByPlayer)
        val useCase = UpdateEventUseCase(repository, PlayerRepositoryDouble(), SubscriberRepositoryDouble())
        val expected = Given.aEvent(eventId = eventId, reservationConfirm = false, state = EventStates.CancelByPlayer)

        useCase.execute(eventId, updateEventDto)

        repository.wasCalled.shouldBeTrue()
        repository.lastUpdateEvent.shouldBe(expected)
    }

    @Test
    fun `validate event id`() {
        val eventId = "validEventId"
        val repository = EventRepositoryDouble(storedEvent = listOf(Given.aEvent(eventId = eventId)))
        val useCase = UpdateEventUseCase(
            repository,
            PlayerRepositoryDouble(storedPlayers = listOf(givenACreatePlayer())),
            SubscriberRepositoryDouble(listOf(givenACreateSubscriber()))
        )

        val result = useCase.isContextValid(eventId, givenAUpdateEventDto())

        repository.wasCalled.shouldBeTrue()
        result.shouldBeTrue()
    }

    @Test
    fun `invalid event id`() {
        val repository = EventRepositoryDouble(storedEvent = listOf(Given.aEvent()))
        val useCase = UpdateEventUseCase(
            repository,
            PlayerRepositoryDouble(storedPlayers = listOf(givenACreatePlayer())),
            SubscriberRepositoryDouble(listOf(givenACreateSubscriber()))
        )

        val result = useCase.isContextValid("invalidEventId", givenAUpdateEventDto())

        repository.wasCalled.shouldBeTrue()
        result.shouldBeFalse()
    }

    @Test
    fun `validate player id`() {
        val playerId = "validPlayerId"
        val playerRepository = PlayerRepositoryDouble(storedPlayers = listOf(givenACreatePlayer(id = playerId)))
        val useCase = UpdateEventUseCase(
            EventRepositoryDouble(storedEvent = listOf(Given.aEvent())),
            playerRepository,
            SubscriberRepositoryDouble(listOf(givenACreateSubscriber()))
        )

        val result = useCase.isContextValid("eventId", givenAUpdateEventDto(playerId = playerId))

        playerRepository.wasCalled.shouldBeTrue()
        result.shouldBeTrue()
    }

    @Test
    fun `invalid player id`() {
        val playerRepository = PlayerRepositoryDouble(storedPlayers = listOf(givenACreatePlayer()))
        val useCase = UpdateEventUseCase(
            EventRepositoryDouble(storedEvent = listOf(Given.aEvent())),
            playerRepository,
            SubscriberRepositoryDouble()
        )

        val result = useCase.isContextValid("eventId", givenAUpdateEventDto(playerId = "invalidPlayerId"))

        playerRepository.wasCalled.shouldBeTrue()
        result.shouldBeFalse()
    }

    @Test
    fun `validate subscriber id`() {
        val subscriberId = "validSubscriberId"
        val dto = givenAUpdateEventDto(subscriberId = subscriberId)
        val subscriberRepository =
            SubscriberRepositoryDouble(storedSubscribers = listOf(givenACreateSubscriber(id = subscriberId)))
        val useCase = UpdateEventUseCase(
            EventRepositoryDouble(storedEvent = listOf(Given.aEvent())),
            PlayerRepositoryDouble(storedPlayers = listOf(givenACreatePlayer())),
            subscriberRepository
        )

        val result = useCase.isContextValid("eventId", dto)

        subscriberRepository.wasCalled.shouldBeTrue()
        result.shouldBeTrue()
    }

    @Test
    fun `invalid subscriber id`() {
        val subscriberRepository =
            SubscriberRepositoryDouble(storedSubscribers = listOf(givenACreateSubscriber()))
        val useCase = UpdateEventUseCase(
            EventRepositoryDouble(storedEvent = listOf(Given.aEvent())),
            PlayerRepositoryDouble(storedPlayers = listOf(givenACreatePlayer())),
            subscriberRepository
        )

        val result = useCase.isContextValid("eventId", givenAUpdateEventDto(subscriberId = "invalidSubscriberId"))

        subscriberRepository.wasCalled.shouldBeTrue()
        result.shouldBeFalse()
    }

    private fun givenAUpdateEventDto(
        playerId: String = "playerId",
        subscriberId: String = "subscriberId",
        date: String = "date",
        reservationConfirm: Boolean = false,
        state: EventStates = EventStates.Created
    ) = UpdateEventDto(playerId, subscriberId, date, reservationConfirm, state)

    private fun givenACreatePlayer(
        id: String = "playerId",
        mail: String = "mail",
        password: String = "password",
        name: String = "name",
        accountNumber: String = "accountNumber",
        events: List<PlayEvent> = listOf()
    ) = CreatePlayer(id, mail, password, name, accountNumber, events)

    private fun givenACreateSubscriber(
        id: String = "subscriberId",
        mail: String = "mail",
        password: String = "password",
        name: String = "name",
        birthDate: String = "accountNumber"
    ) = CreateSubscriber(id, mail, password, name, birthDate)
}

