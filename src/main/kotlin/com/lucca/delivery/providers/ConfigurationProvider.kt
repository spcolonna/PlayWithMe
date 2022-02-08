package com.lucca.delivery.providers

import com.lucca.delivery.AppConfig
import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.yaml.YamlParser
import io.ktor.util.*

object ConfigurationProvider {
    private const val configurationFileName = "/config.yaml"

    data class Configuration @OptIn(KtorExperimentalAPI::class) constructor(
        val app: AppConfig,
        val http: GatewaysProvider.HttpEngineConfiguration,
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