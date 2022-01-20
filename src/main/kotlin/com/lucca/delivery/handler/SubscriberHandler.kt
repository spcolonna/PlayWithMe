package com.lucca.delivery.handler

import com.lucca.delivery.Handler
import com.lucca.delivery.presenter.SubscriberPresenter
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.runBlocking

class SubscriberHandler(private val presenter: SubscriberPresenter):Handler {
    lateinit var application: Application

    override fun routing(a: Application) {
        application = a
        a.routing {
            get("/subscribers") {
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

class ResponseBuilder(private val call: ApplicationCall) {
    fun onValid(element: Any) = runBlocking {
        call.respond(HttpStatusCode.OK, element)
    }

    fun onValid() = runBlocking {
        call.respond(HttpStatusCode.OK)
    }

    fun onConflict(element: Any) = runBlocking {
        call.respond(HttpStatusCode.Conflict, element)
    }

    fun onNotAcceptable() = runBlocking {
        call.respond(HttpStatusCode.NotAcceptable)
    }

    fun onNotAcceptable(element: Any) = runBlocking {
        call.respond(HttpStatusCode.NotAcceptable, element)
    }

    fun onUnauthenticated() = runBlocking {
        call.respond(HttpStatusCode.Unauthorized)
    }

    fun onCreated(element: Any) = runBlocking {
        call.respond(HttpStatusCode.Created, element)
    }
}
