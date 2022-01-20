package com.lucca.infra.dataBase.redis

import com.lucca.domain.entity.CreateSubscriber
import com.lucca.domain.interace.ISubscriberRepository
import com.lucca.infra.service.RedisService
import io.lettuce.core.api.coroutines.RedisCoroutinesCommands
import kotlinx.coroutines.runBlocking

class SubscriberRepository(
    private val connection: RedisCoroutinesCommands<String, String>,
    private val service: RedisService
) : ISubscriberRepository {

    private val baseKey = "subscriber:"

    override fun store(createSubscriber: CreateSubscriber) {
        runBlocking {
            service.store(connection, createBaseKeyWith(createSubscriber.id), createSubscriber)
        }
    }

    override fun isMailAvailable(mail: String): Boolean =
        runBlocking {
            service.has(connection,mail)
        }

    private fun createBaseKeyWith(id: String) = baseKey + id
}

