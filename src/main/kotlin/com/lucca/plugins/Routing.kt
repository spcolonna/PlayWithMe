package com.lucca.plugins

import com.lucca.delivery.providers.RepositoryProvider
import com.lucca.delivery.handler.SubscriberHandler
import com.lucca.delivery.interfaces.Handler
import com.lucca.delivery.presenter.SubscriberPresenter
import com.lucca.delivery.providers.HandlerProvider
import com.lucca.domain.useCase.subscriber.CreateSubscriberUseCase
import com.lucca.infra.service.IdGenerator
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*

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

fun getHandlers() = HandlerProvider.loaderHandler()


