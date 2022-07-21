package playwithyou.lucca.delivery.handler


import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import playwithyou.lucca.delivery.`interface`.Handler
import playwithyou.lucca.delivery.presenter.HousePresenter

class HousesHandler (private val presenter: HousePresenter): Handler {
    private lateinit var application: Application

    override fun routing(app: Application) {
        application = app
        app.routing {
            route("/house") {
                post {
                    presenter.register(call.receive())
                }
            }
        }
    }
}