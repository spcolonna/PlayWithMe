package com.lucca.delivery

import com.lucca.delivery.ConfigurationProvider.config
import com.lucca.infra.dataBase.redis.SubscriberRepository
import com.lucca.infra.service.RedisService
import io.lettuce.core.RedisClient
import io.lettuce.core.RedisURI
import io.lettuce.core.api.coroutines
import io.lettuce.core.api.coroutines.RedisCoroutinesCommands
import org.slf4j.LoggerFactory

object RepositoryProvider {
    private val logger = LoggerFactory.getLogger(this::class.java)
    var connected: Boolean = false

    data class PersistenceConfiguration(
        val type: PersistenceType,
        val redis: RedisConfiguration
    )

    enum class PersistenceType {
        REDIS,
        IN_MEMORY
    }

    data class RedisConfiguration(
        val endpoint: String,
        val password: String
    )

    val connection: RedisCoroutinesCommands<String, String> by lazy {
        logger.info("Connecting to redis instance...")
        buildRedisClient()
            .connect()
            .coroutines()
            .also {
                logger.info("Connection with redis established")
            }
    }

    val service = RedisService()

    private fun buildRedisClient(): RedisClient {
        val uri = RedisURI.create("redis://${config.persistence.redis.endpoint}")
        uri.password = config.persistence.redis.password.toCharArray()
        return RedisClient.create(uri)
    }


    val subscriberRepo by lazy {
        logger.info("Persistence type is ${config.persistence.type}")
        SubscriberRepository(connection, service)
    }

}