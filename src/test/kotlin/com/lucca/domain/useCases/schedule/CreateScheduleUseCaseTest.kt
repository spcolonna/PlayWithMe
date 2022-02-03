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
        val useCase = givenACreateScheduleUseCase(scheduleId)
        val expected = givenAScheduler(scheduleId, playerId, availableDays, availableHours)

        val result = useCase.execute(givenACreateSchedulerDto(playerId, availableDays, availableHours))

        SchedulerRepositoryDouble().wasCalled.shouldBeTrue()
        result.shouldBe(scheduleId)
        SchedulerRepositoryDouble().lastScheduleStored.shouldBe(expected)
    }

    private fun givenACreateScheduleUseCase(scheduleId: String) =
        CreateScheduleUseCase(SchedulerRepositoryDouble(), IdGeneratorDouble(scheduleId))

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

