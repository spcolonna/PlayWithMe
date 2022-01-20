package com.lucca.delivery.presenter

import com.lucca.delivery.response.ResponseBuilder
import com.lucca.delivery.request.CreateSubscriberRequest
import com.lucca.domain.useCase.subscriber.CreateSubscriberUseCase

class SubscriberPresenter(private val useCase: CreateSubscriberUseCase) {
    fun createSubscriber(request: CreateSubscriberRequest, responseBuilder: ResponseBuilder) {
        val dto = request.toDto()
        if(useCase.isContextValid(dto)){
            responseBuilder.onValid(useCase.execute(dto))
        }else
            responseBuilder.onConflict("Algo salió mal")

    }
}