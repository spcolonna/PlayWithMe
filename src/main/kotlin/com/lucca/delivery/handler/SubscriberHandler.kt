package com.lucca.delivery.handler

import com.lucca.delivery.Handler
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*

class SubscriberHandler:Handler {
    lateinit var application: Application

    override fun routing(a: Application) {
        application = a
        a.routing {
            get("/subscribers") {
                call.respondText("Call Subscriber")
            }
            // Static plugin. Try to access `/static/index.html`
            static("/static") {
                resources("static")
            }
        }
    }
}