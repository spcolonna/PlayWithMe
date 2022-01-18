package playwithyou.lucca.delivery.handler

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*
import playwithyou.lucca.delivery.`interface`.Handler
import playwithyou.lucca.delivery.presenter.SubscriberPresenter

class SubscribersHandler(private val presenter: SubscriberPresenter):Handler {
    private lateinit var application: Application

    override fun routing(a: Application) {
        application = a
        a.routing {
            route("/subscribers"){
                post {
                    presenter.register(call.receive())
                }
            }
        }
    }
}

