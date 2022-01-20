package com.lucca.domain.useCase.player

import com.lucca.delivery.dto.CreatePlayerDto
import com.lucca.domain.Builder
import com.lucca.domain.interace.IIdGenerator
import com.lucca.domain.interace.IPlayerRepository

class CreatePlayerUseCase(private val idGenerator: IIdGenerator, private val playerRepositoryDouble: IPlayerRepository) {
    fun execute(dto: CreatePlayerDto): String {
        val id =idGenerator.getId()
        playerRepositoryDouble.store(Builder.createPlayerFromDto(id, dto))
        return id
    }

}
