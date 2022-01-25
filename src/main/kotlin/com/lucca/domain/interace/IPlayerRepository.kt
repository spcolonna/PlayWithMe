package com.lucca.domain.interace

import com.lucca.domain.entity.CreatePlayer

interface IPlayerRepository {
    fun store(createPlayer: CreatePlayer)
    fun isPlayerValid(playerId: String): Boolean
}