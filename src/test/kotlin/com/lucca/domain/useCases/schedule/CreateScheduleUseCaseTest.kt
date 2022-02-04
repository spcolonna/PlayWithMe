package com.lucca.domain.useCases.schedule

import com.lucca.delivery.dto.CreateSchedulerDto
import com.lucca.domain.Given
import com.lucca.domain.doubles.IdGeneratorDouble
import com.lucca.domain.doubles.PlayerRepositoryDouble
import com.lucca.domain.doubles.SchedulerRepositoryDouble
import com.lucca.domain.entity.CreatePlayer
import com.lucca.domain.entity.PlayEvent
import com.lucca.domain.enums.DaysEnum
import com.lucca.domain.useCase.scheduler.CreateScheduleUseCase
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.Test

class CreateScheduleUseCaseTest {

    @Test
    fun `create schedule from player`() {
        val scheduleId = "scheduleId"
        val playerId = "playerId"
        val availableDays = listOf<DaysEnum>()
        val availableHours = listOf<Int>()
        val repository = SchedulerRepositoryDouble()
        val useCase = CreateScheduleUseCase(repository, PlayerRepositoryDouble(), IdGeneratorDouble(scheduleId))
        val expected = Given.aScheduler(scheduleId, playerId, availableDays, availableHours)

        val result = useCase.execute(givenACreateSchedulerDto(playerId, availableDays, availableHours))

        repository.wasCalled.shouldBeTrue()
        result.shouldBe(scheduleId)
        repository.lastScheduleStored.shouldBe(expected)
    }

    @Test
    fun `create schedule from another player`() {
        val scheduleId = "anotherScheduleId"
        val playerId = "anotherPlayerId"
        val availableDays = listOf(DaysEnum.Monday, DaysEnum.Wednesday, DaysEnum.Friday)
        val availableHours = listOf(15,16,17)
        val repository = SchedulerRepositoryDouble()
        val useCase = CreateScheduleUseCase(repository, PlayerRepositoryDouble(), IdGeneratorDouble(scheduleId))
        val expected = Given.aScheduler(scheduleId, playerId, availableDays, availableHours)

        val result = useCase.execute(givenACreateSchedulerDto(playerId, availableDays, availableHours))

        repository.wasCalled.shouldBeTrue()
        result.shouldBe(scheduleId)
        repository.lastScheduleStored.shouldBe(expected)
    }

    @Test
    fun `validate player id`(){
        val playerId = "playerId"
        val playerRepository = PlayerRepositoryDouble(storedPlayers = listOf(givenACreatePlayer(id = playerId)))
        val useCase = CreateScheduleUseCase(SchedulerRepositoryDouble(), playerRepository, IdGeneratorDouble())

        val result = useCase.isContextValid(playerId)

        playerRepository.wasCalled.shouldBeTrue()
        result.shouldBeTrue()
    }

    private fun givenACreateSchedulerDto(
        playerId: String = "playerId",
        availableDays: List<DaysEnum> = listOf(),
        availableHours: List<Int> = listOf()
    ) = CreateSchedulerDto(playerId, availableDays, availableHours)

    private fun givenACreatePlayer(
        id: String = "playerId",
        mail: String = "mail",
        password: String = "password",
        name: String = "name",
        accountNumber: String = "accountNumber",
        events: List<PlayEvent> = listOf()
    ) = CreatePlayer(id, mail, password, name, accountNumber, events)
}

