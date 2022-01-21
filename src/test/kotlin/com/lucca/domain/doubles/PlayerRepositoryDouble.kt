package com.lucca.domain.doubles

import com.lucca.domain.entity.CreatePlayer
import com.lucca.domain.interace.IPlayerRepository

class PlayerRepositoryDouble : IPlayerRepository {
    lateinit var lastCreatePlayer: CreatePlayer
    var wasCalled: Boolean = false

    override fun store(createPlayer: CreatePlayer) {
        wasCalled = true
        lastCreatePlayer = createPlayer
    }
}