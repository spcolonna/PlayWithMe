package playwithyou.lucca.delivery.handler

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*
import playwithyou.lucca.delivery.`interface`.Handler
import playwithyou.lucca.delivery.presenter.SellerPresenter

class SellersHandler(private val presenter: SellerPresenter) : Handler {
    private lateinit var application: Application

    override fun routing(app: Application) {
        application = app
        app.routing {
            route("/sellers") {
                post {
                    presenter.register(call.receive())
                }
            }
        }
    }
}

