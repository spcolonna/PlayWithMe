package playwithyou.lucca.delivery.presenter

import playwithyou.lucca.delivery.ResponseBuilder
import playwithyou.lucca.delivery.requests.house.AroundRequest
import playwithyou.lucca.delivery.requests.house.CreateHouseRequest
import playwithyou.lucca.delivery.responses.IdResponse
import playwithyou.lucca.domain.useCase.house.CreateHouseUseCase

class HousePresenter(private val createHouseUseCase: CreateHouseUseCase) {
    fun register(request: CreateHouseRequest, builder: ResponseBuilder) {
        val dto = request.toDto()
        if(createHouseUseCase.validateDto(dto))
            builder.onValid(IdResponse(createHouseUseCase.execute(dto)))
        else
            builder.onInvalid("mail conflict")
    }

    fun around(request: AroundRequest, response: ResponseBuilder) {

    }

}
