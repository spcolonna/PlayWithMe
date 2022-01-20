package com.lucca.delivery.response

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import kotlinx.coroutines.runBlocking

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