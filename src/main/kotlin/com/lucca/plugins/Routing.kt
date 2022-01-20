package com.lucca.plugins

import com.lucca.delivery.Handler
import com.lucca.delivery.handler.SubscriberHandler
import com.lucca.delivery.presenter.SubscriberPresenter
import com.lucca.domain.useCase.subscriber.CreateSubscriberUseCase
import io.ktor.routing.*
import io.ktor.http.content.*
import io.ktor.application.*
import io.ktor.response.*

fun Application.configureRouting() {
    //getHandlers().forEach { it.routing(this) }

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

/*fun getHandlers() = listOf(
    SubscriberHandler(SubscriberPresenter(CreateSubscriberUseCase(Idg)))
)*/


