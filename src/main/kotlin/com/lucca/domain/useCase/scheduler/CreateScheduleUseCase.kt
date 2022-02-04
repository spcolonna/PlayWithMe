package com.lucca.domain.useCase.scheduler

import com.lucca.delivery.dto.CreateSchedulerDto
import com.lucca.domain.Builder
import com.lucca.domain.interace.IIdGenerator
import com.lucca.domain.interace.IPlayerRepository
import com.lucca.domain.interace.ISchedulerRepository

class CreateScheduleUseCase(
    private val repository: ISchedulerRepository,
    private val playerRepository: IPlayerRepository,
    private val idGenerator: IIdGenerator
) {
    fun execute(dto: CreateSchedulerDto): String {
        val schedulerId = idGenerator.getId()
        repository.store(Builder.createSchedulerFromDto(schedulerId, dto))
        return schedulerId
    }

    fun isContextValid(playerId: String) = playerRepository.has(playerId)

}