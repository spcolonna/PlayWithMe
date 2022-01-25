package com.lucca.domain.doubles

import com.lucca.domain.entity.CreatePlayer
import com.lucca.domain.interace.IPlayerRepository

class PlayerRepositoryDouble(private val storedPlayers: List<CreatePlayer> = listOf()) : IPlayerRepository {
    lateinit var lastCreatePlayer: CreatePlayer
    var wasCalled: Boolean = false

    override fun store(createPlayer: CreatePlayer) {
        wasCalled = true
        lastCreatePlayer = createPlayer
    }

    override fun isPlayerValid(playerId: String): Boolean {
        wasCalled = true
        return storedPlayers.any { it.id == playerId }
    }
}