package com.lucca.infra.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.lettuce.core.api.coroutines.RedisCoroutinesCommands

class RedisService {
    private val mapper = jacksonObjectMapper()

    suspend fun <T> store(connection: RedisCoroutinesCommands<String, String>, key: String, value: T) {
        connection.set(key, value.asString())
    }

    suspend fun has(connection: RedisCoroutinesCommands<String, String>, key: String): Boolean =
        connection.exists(key)!! > 0

    private fun <T> T.asString(): String = mapper.writeValueAsString(this)

}