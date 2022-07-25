package playwithyou.lucca.delivery.presenter

import playwithyou.lucca.delivery.ResponseBuilder
import playwithyou.lucca.delivery.requests.house.CreateHouseRequest
import playwithyou.lucca.delivery.requests.house.HouseAroundRequest
import playwithyou.lucca.delivery.responses.HouseListResponse
import playwithyou.lucca.delivery.responses.IdResponse
import playwithyou.lucca.domain.useCase.house.CreateHouseUseCase
import playwithyou.lucca.domain.useCase.house.GetHousesAroundUseCase

class HousePresenter(private val createHouseUseCase: CreateHouseUseCase, private val getHousesAroundUseCase: GetHousesAroundUseCase){
    fun register(request: CreateHouseRequest, builder: ResponseBuilder) {
        val dto = request.toDto()
        if(createHouseUseCase.validateDto(dto))
            builder.onValid(IdResponse(createHouseUseCase.execute(dto)))
        else
            builder.onInvalid("mail conflict")
    }

    fun around(request: HouseAroundRequest, response: ResponseBuilder) {
        response.onValid(HouseListResponse(getHousesAroundUseCase.execute(request.toDto())))
    }

}

