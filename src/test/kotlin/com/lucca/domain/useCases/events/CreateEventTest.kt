package com.lucca.domain.useCases.events

import com.lucca.delivery.dto.CreateEventDto
import com.lucca.domain.Given
import com.lucca.domain.doubles.EventRepositoryDouble
import com.lucca.domain.doubles.IdGeneratorDouble
import com.lucca.domain.doubles.PlayerRepositoryDouble
import com.lucca.domain.doubles.SubscriberRepositoryDouble
import com.lucca.domain.entity.CreatePlayer
import com.lucca.domain.entity.CreateSubscriber
import com.lucca.domain.entity.PlayEvent
import com.lucca.domain.useCase.events.CreateEventUseCase
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.Test

class CreateEventTest {

    @Test
    fun `create event`() {
        val eventId = "eventId"
        val repository = EventRepositoryDouble()
        val expected = Given.aEvent(eventId = eventId, reservationConfirm = false)
        val useCase = CreateEventUseCase(
            IdGeneratorDouble(eventId),
            repository,
            PlayerRepositoryDouble(),
            SubscriberRepositoryDouble()
        )

        val result = useCase.execute(givenACreateEventDto())

        result.shouldBe(eventId)
        repository.wasCalled.shouldBeTrue()
        repository.lastCreateEvent.shouldBe(expected)
    }

    @Test
    fun `create another event`() {
        val eventId = "anotherEventId"
        val repository = EventRepositoryDouble()
        val expected = Given.aEvent(eventId = eventId, reservationConfirm = false)
        val useCase = CreateEventUseCase(
            IdGeneratorDouble(eventId),
            repository,
            PlayerRepositoryDouble(),
            SubscriberRepositoryDouble()
        )

        val result = useCase.execute(givenACreateEventDto())

        result.shouldBe(eventId)
        repository.wasCalled.shouldBeTrue()
        repository.lastCreateEvent.shouldBe(expected)
    }

    @Test
    fun `validate exist playerId`() {
        val playerId = "pId"
        val dto = givenACreateEventDto(playerId = playerId)
        val playerRepository = PlayerRepositoryDouble(listOf(givenACreatePlayer(id = playerId)))
        val useCase = CreateEventUseCase(
            IdGeneratorDouble(),
            EventRepositoryDouble(),
            playerRepository,
            SubscriberRepositoryDouble(listOf(givenACreateSubscriber(id = dto.subscriberId)))
        )

        val result = useCase.isContextValid(dto)

        playerRepository.wasCalled.shouldBeTrue()
        result.shouldBeTrue()
    }

    @Test
    fun `invalid exist playerId`() {
        val dto = givenACreateEventDto(playerId = "invalidPlayerId")
        val playerRepository = PlayerRepositoryDouble(listOf(givenACreatePlayer(id = "playerId")))
        val useCase = CreateEventUseCase(
            IdGeneratorDouble(),
            EventRepositoryDouble(),
            playerRepository,
            SubscriberRepositoryDouble(listOf(givenACreateSubscriber(id = dto.subscriberId)))
        )

        val result = useCase.isContextValid(dto)

        playerRepository.wasCalled.shouldBeTrue()
        result.shouldBeFalse()
    }

    @Test
    fun `validate exist subscriberId`() {
        val subId = "subId"
        val dto = givenACreateEventDto(subscriberId = subId)
        val subRepository = SubscriberRepositoryDouble(listOf(givenACreateSubscriber(id = subId)))

        val useCase = CreateEventUseCase(
            IdGeneratorDouble(),
            EventRepositoryDouble(),
            PlayerRepositoryDouble(listOf(givenACreatePlayer(id = dto.playerId))),
            subRepository
        )

        val result = useCase.isContextValid(dto)

        subRepository.wasCalled.shouldBeTrue()
        result.shouldBeTrue()
    }

    @Test
    fun `invalid exist subscriberId`() {
        val dto = givenACreateEventDto(playerId = "invalidSubId")
        val subRepository = SubscriberRepositoryDouble(listOf(givenACreateSubscriber(id = "subId")))
        val useCase = CreateEventUseCase(
            IdGeneratorDouble(),
            EventRepositoryDouble(),
            PlayerRepositoryDouble(listOf(givenACreatePlayer(id = dto.playerId))),
            subRepository
        )

        val result = useCase.isContextValid(dto)

        subRepository.wasCalled.shouldBeTrue()
        result.shouldBeFalse()
    }

    private fun givenACreateEventDto(
        playerId: String = "playerId",
        subscriberId: String = "subscriberId",
        date: String = "date"
    ) = CreateEventDto(playerId, subscriberId, date)

    private fun givenACreatePlayer(
        id: String = "id",
        mail: String = "mail",
        password: String = "password",
        name: String = "name",
        accountNumber: String = "accountNumber",
        events: List<PlayEvent> = listOf()
    ) = CreatePlayer(id, mail, password, name, accountNumber, events)

    private fun givenACreateSubscriber(
        id: String = "id",
        mail: String = "mail",
        password: String = "password",
        name: String = "name",
        birthDate: String = "accountNumber"
    ) = CreateSubscriber(id, mail, password, name, birthDate)
}

