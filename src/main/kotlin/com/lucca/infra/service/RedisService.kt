package com.lucca.infra.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.lettuce.core.RedisClient

class RedisService {
    private var redisClient: RedisClient = RedisClient.create("redis://password@localhost:6379/0")
    private val mapper = jacksonObjectMapper()

    fun <T> store(key: String, value: T) {
        getExecuteCommand().set(key, value.asString())
        closeConnection()
    }

    fun has(key:String):Boolean{
        val result = getExecuteCommand().exists(key)!! > 0
        closeConnection()

        return result
    }

    private fun closeConnection() {
        redisClient.connect().close()
    }

    private fun getExecuteCommand() = redisClient.connect().sync()

    /*suspend fun <T> store(connection: RedisCoroutinesCommands<String, String>, key: String, value: T) {
        connection.set(key, value.asString())
    }*/
    /*suspend fun has(connection: RedisCoroutinesCommands<String, String>, key: String): Boolean {
        return connection.exists(key)!! > 0
    }*/

    private fun <T> T.asString(): String = mapper.writeValueAsString(this)

}