package com.lucca.domain.useCases.schedule

import com.lucca.delivery.dto.CreateSchedulerDto
import com.lucca.domain.doubles.IdGeneratorDouble
import com.lucca.domain.doubles.SchedulerRepositoryDouble
import com.lucca.domain.entity.Scheduler
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
        val useCase = CreateScheduleUseCase(repository, IdGeneratorDouble(scheduleId))
        val expected = givenAScheduler(scheduleId, playerId, availableDays, availableHours)

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
        val useCase = CreateScheduleUseCase(repository, IdGeneratorDouble(scheduleId))
        val expected = givenAScheduler(scheduleId, playerId, availableDays, availableHours)

        val result = useCase.execute(givenACreateSchedulerDto(playerId, availableDays, availableHours))

        repository.wasCalled.shouldBeTrue()
        result.shouldBe(scheduleId)
        repository.lastScheduleStored.shouldBe(expected)
    }

    @Test
    fun `validate player id`(){

    }

    private fun givenACreateSchedulerDto(
        playerId: String = "playerId",
        availableDays: List<DaysEnum> = listOf(),
        availableHours: List<Int> = listOf()
    ): CreateSchedulerDto {
        return CreateSchedulerDto(playerId, availableDays, availableHours)
    }

    private fun givenAScheduler(
        scheduleId: String = "scheduleId",
        playerId: String = "playerId",
        availableDays: List<DaysEnum> = listOf(),
        availableHours: List<Int> = listOf()
    ) = Scheduler(scheduleId, playerId, availableDays, availableHours)
}

