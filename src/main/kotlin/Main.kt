import playwithyou.lucca.delivery.HttpApiServer

fun main() {
    println("Start Program...")
    MongoClientSingleton().init()
    val httpServer = HttpApiServer()
    httpServer.start()
}