package playwithyou.lucca.delivery.presenter

import playwithyou.lucca.delivery.ResponseBuilder
import playwithyou.lucca.delivery.requests.CreateHouseRequest
import playwithyou.lucca.domain.useCase.house.CreateHouseUseCase

class HousePresenter(private val createSellerUseCase: CreateHouseUseCase) {
    fun register(request: CreateHouseRequest, builder: ResponseBuilder) {
        TODO("Not yet implemented")
    }

}
