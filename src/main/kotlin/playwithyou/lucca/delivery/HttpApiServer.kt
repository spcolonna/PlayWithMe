package playwithyou.lucca.delivery

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory
import playwithyou.lucca.delivery.handler.HousesHandler
import playwithyou.lucca.delivery.`interface`.Handler
import playwithyou.lucca.delivery.presenter.SellerPresenter
import playwithyou.lucca.delivery.handler.SellersHandler
import playwithyou.lucca.delivery.presenter.HousePresenter
import playwithyou.lucca.domain.useCase.house.CreateHouseUseCase
import playwithyou.lucca.domain.useCase.house.GetHousesAroundUseCase
import playwithyou.lucca.domain.useCase.seller.CreateSellerUseCase
import playwithyou.lucca.infrastructure.repositories.HouseRepository
import playwithyou.lucca.infrastructure.repositories.SellerRepository
import playwithyou.lucca.infrastructure.services.IdGenerator

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
        val idGenerator = IdGenerator()
        val sellerRepository = SellerRepository()
        val houseRepository = HouseRepository()

        return listOf(
            SellersHandler(SellerPresenter(CreateSellerUseCase(idGenerator,sellerRepository))),
            HousesHandler(HousePresenter(
                CreateHouseUseCase(houseRepository,sellerRepository,idGenerator),
                GetHousesAroundUseCase(houseRepository)
            ))
        )
    }
}
