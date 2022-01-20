package com.lucca.delivery.presenter

import com.lucca.delivery.handler.ResponseBuilder
import com.lucca.delivery.request.CreateSubscriberRequest
import com.lucca.domain.useCase.subscriber.CreateSubscriberUseCase

class SubscriberPresenter(private val createSubscriberUseCase: CreateSubscriberUseCase) {
    fun createSubscriber(request: CreateSubscriberRequest, responseBuilder: ResponseBuilder) {
        createSubscriberUseCase.execute(request.toDto())
    }
}