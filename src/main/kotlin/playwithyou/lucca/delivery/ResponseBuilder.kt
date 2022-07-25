package playwithyou.lucca.delivery

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.coroutines.runBlocking
import playwithyou.lucca.delivery.responses.HouseListResponse
import playwithyou.lucca.delivery.responses.IdResponse


class ResponseBuilder(private val call: ApplicationCall) {
    fun onValid(body: IdResponse) = runBlocking {
        call.respond(HttpStatusCode.OK, body.id)
    }

    fun onValid(body: HouseListResponse) = runBlocking {
        val objectMapper = ObjectMapper()
        try {
            val json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body)
            println(json)
            call.respond(HttpStatusCode.OK, json)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    fun onInvalid(body: Any) = runBlocking {
        call.respond(HttpStatusCode.Conflict, body)
    }


}