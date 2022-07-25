package playwithyou.lucca.delivery.presenter

import playwithyou.lucca.delivery.ResponseBuilder
import playwithyou.lucca.delivery.requests.seller.CreateSellerRequest
import playwithyou.lucca.delivery.responses.IdResponse
import playwithyou.lucca.domain.useCase.seller.CreateSellerUseCase


class SellerPresenter(private val createSellerUseCase: CreateSellerUseCase) {
    fun register(request: CreateSellerRequest, builder: ResponseBuilder) {
        val dto = request.toDto()
        if(createSellerUseCase.validateDto(dto))
            builder.onValid(IdResponse(createSellerUseCase.execute(dto)))
        else
            builder.onInvalid("mail conflict")
    }

    fun get(id: String) {
        TODO("Not yet implemented")
    }

}

