package com.lucca.delivery.providers

import com.lucca.delivery.providers.ConfigurationProvider
import io.ktor.client.engine.apache.*

object GatewaysProvider {

    data class HttpEngineConfiguration(
        val connectTimeoutMs: Int,
        val connectionRequestTimeoutMs: Int,
        val maxConnTotal: Int,
        val maxConnPerRoute: Int
    )

    private val engine by lazy {
        val cfg = ConfigurationProvider.config.http

        Apache.create {
            connectTimeout = cfg.connectTimeoutMs
            connectionRequestTimeout = cfg.connectionRequestTimeoutMs

            customizeClient {
                // Maximum number of socket connections.
                setMaxConnTotal(cfg.maxConnTotal)

                // Maximum number of requests for a specific endpoint route.
                setMaxConnPerRoute(cfg.maxConnPerRoute)
            }
        }
    }
}