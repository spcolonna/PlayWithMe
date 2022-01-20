package com.lucca.plugins

import com.lucca.delivery.RepositoryProvider
import com.lucca.delivery.RepositoryProvider.connection
import com.lucca.delivery.handler.SubscriberHandler
import com.lucca.delivery.presenter.SubscriberPresenter
import com.lucca.domain.useCase.subscriber.CreateSubscriberUseCase
import com.lucca.infra.dataBase.redis.SubscriberRepository
import com.lucca.infra.service.IdGenerator
import com.lucca.infra.service.RedisService
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.lettuce.core.api.coroutines.RedisCoroutinesCommands

fun Application.configureRouting() {

    getHandlers().forEach { it.routing(this) }

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}

fun getHandlers() = listOf(
    SubscriberHandler(
        SubscriberPresenter(
            CreateSubscriberUseCase(
                IdGenerator(),
                RepositoryProvider.subscriberRepo
            )
        )
    )
)


