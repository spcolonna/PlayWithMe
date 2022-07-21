package playwithyou.lucca.delivery.presenter

import playwithyou.lucca.delivery.ResponseBuilder
import playwithyou.lucca.delivery.requests.CreateSellerRequest
import playwithyou.lucca.delivery.responses.IdResponse
import playwithyou.lucca.domain.useCase.seller.CreateSellerUseCase


class SellerPresenter(private val createSellerUseCase: CreateSellerUseCase) {
    fun register(request: CreateSellerRequest, builder: ResponseBuilder) {
        builder.onValid(IdResponse("NeedToCallUseCase"))
    }

    fun get(id: String) {
        TODO("Not yet implemented")
    }

}

