package playwithyou.lucca.delivery

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.LoggerFactory
import playwithyou.lucca.delivery.`interface`.Handler
import playwithyou.lucca.delivery.presenter.SubscriberPresenter
import playwithyou.lucca.delivery.handler.SubscribersHandler

class HttpApiServer {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun start() {
        embeddedServer(Netty, port = 8080) {
            main()
        }.start(wait = true)
    }

    private fun Application.main() {
        routing {
            if(logger.isTraceEnabled)
                trace { logger.trace(it.buildText()) }
        }
        
        val handlers = getHandlers()
        handlers.forEach { it.routing(this) }
    }

    private fun getHandlers(): List<Handler> {
        return listOf(
            SubscribersHandler(SubscriberPresenter())
        )
    }
}
