package com.lucca.delivery.handler

import com.lucca.delivery.interfaces.Handler
import com.lucca.delivery.presenter.SubscriberPresenter
import com.lucca.delivery.response.ResponseBuilder
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

class SubscriberHandler(private val presenter: SubscriberPresenter): Handler {
    lateinit var application: Application

    override fun routing(a: Application) {
        application = a
        a.routing {
            post("/subscribers") {
                presenter.createSubscriber(call.receive(), ResponseBuilder(call))
                call.respondText("Call Subscriber")
            }
            // Static plugin. Try to access `/static/index.html`
            static("/static") {
                resources("static")
            }
        }
    }
}

