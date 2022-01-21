package com.lucca.domain.useCases.player

import com.lucca.delivery.dto.CreatePlayerDto
import com.lucca.domain.doubles.IdGeneratorDouble
import com.lucca.domain.doubles.PlayerRepositoryDouble
import com.lucca.domain.entity.CreatePlayer
import com.lucca.domain.useCase.player.CreatePlayerUseCase
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.Test

class CreatePlayerUseCaseTest {
    @Test
    fun `create player`() {
        val id = "playerId"
        val playerDto = givenAPlayerDto()
        val playerRepo = PlayerRepositoryDouble()
        val expectedCreatePlayer = givenACreatePlayer(id = id)
        val useCase = CreatePlayerUseCase(IdGeneratorDouble(id = id), playerRepo)

        val result = useCase.execute(playerDto)

        result.shouldBe(id)
        playerRepo.wasCalled.shouldBeTrue()
        playerRepo.lastCreatePlayer.shouldBe(expectedCreatePlayer)
    }

    private fun givenACreatePlayer(
        id: String = "id",
        mail: String = "mail",
        password: String = "password",
        name: String = "name",
        accountNumber: String = "accountNumber"
    ) = CreatePlayer(id, mail, password, name, accountNumber, listOf())

    private fun givenAPlayerDto(
        mail: String = "mail",
        password: String = "password",
        name: String = "name",
        accountNumber: String = "accountNumber"
    ) = CreatePlayerDto(mail, password, name, accountNumber)
}

