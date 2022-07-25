package playwithyou.lucca.delivery.handler


import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import playwithyou.lucca.delivery.ResponseBuilder
import playwithyou.lucca.delivery.`interface`.Handler
import playwithyou.lucca.delivery.presenter.HousePresenter
import playwithyou.lucca.delivery.requests.house.CreateHouseRequest

class HousesHandler (private val presenter: HousePresenter): Handler {
    private lateinit var application: Application
    private val format = Json { ignoreUnknownKeys = true }

    override fun routing(app: Application) {
        application = app
        app.routing {
            route("/house") {
                post {
                    val request = format.decodeFromString<CreateHouseRequest>(call.receiveText())
                    val response = ResponseBuilder(call)
                    presenter.register(request, response)
                }
            }

            route("/around") {
                post {
                    val request = format.decodeFromString<CreateHouseRequest>(call.receiveText())
                    val response = ResponseBuilder(call)
                    presenter.around(request, response)
                }
            }
        }
    }
}