package playwithyou.lucca.delivery

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.coroutines.runBlocking

class ResponseBuilder(private val call: ApplicationCall) {
    fun onValid(body: Any) = runBlocking {
        call.respond(HttpStatusCode.OK)
    }

    fun onError(body: Any) = runBlocking {
        call.respond(HttpStatusCode.Conflict, body)
    }
}