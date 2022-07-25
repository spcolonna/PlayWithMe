package playwithyou.lucca.delivery.handler

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import playwithyou.lucca.delivery.ResponseBuilder
import playwithyou.lucca.delivery.`interface`.Handler
import playwithyou.lucca.delivery.presenter.SellerPresenter
import playwithyou.lucca.delivery.requests.seller.CreateSellerRequest


class SellersHandler(private val presenter: SellerPresenter) : Handler {
    private lateinit var application: Application
    private val format = Json { ignoreUnknownKeys = true }

    override fun routing(app: Application) {
        application = app
        app.routing {

            route("/seller") {
                post {
                    val request = format.decodeFromString<CreateSellerRequest>(call.receiveText())
                    val response = ResponseBuilder(call)
                    presenter.register(request, response)
                }
            }

            route("/seller/{id}") {
                get {
                    presenter.get(call.parameters["id"]!!)
                }
                /*put {
                    presenter.update(call.parameters["id"]!!, call.receive())
                }
                delete {
                    presenter.delete(call.parameters["id"]!!)
                }*/
            }
        }
    }
}

