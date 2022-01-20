package com.lucca.delivery

import com.lucca.delivery.ConfigurationProvider.config
import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.yaml.YamlParser
import io.ktor.client.engine.apache.*
import io.ktor.util.*

object ConfigurationProvider {
    private const val configurationFileName = "/config.yaml"

    data class Configuration @OptIn(KtorExperimentalAPI::class) constructor(
        val app: AppConfig,
        val http: GatewaysProvider.HttpEngineConfiguration,
        val platformUrl: String,
        val persistence: RepositoryProvider.PersistenceConfiguration
    )

    /**
     * In charge of obtaining the configuration values and setting them in the corresponding variables.
     */
    val config: Configuration = ConfigLoader.Builder()
        .addFileExtensionMapping("yaml", YamlParser())
        .build()
        .loadConfigOrThrow(configurationFileName)
}

data class AppConfig(
    val image: String,
    val deployedAt: String,
    val port: Int
) {
    val name = image.splitToSequence(':').elementAtOrElse(0) { "N/A" }
    val version = image.splitToSequence(':').elementAtOrElse(1) { "N/A" }
}

object GatewaysProvider {

    data class HttpEngineConfiguration(
        val connectTimeoutMs: Int,
        val connectionRequestTimeoutMs: Int,
        val maxConnTotal: Int,
        val maxConnPerRoute: Int
    )

    private val engine by lazy {
        val cfg = config.http

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
