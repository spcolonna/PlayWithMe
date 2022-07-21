package playwithyou.lucca.delivery.`interface`

import io.ktor.server.application.*


interface Handler {
    fun routing(app: Application)
}