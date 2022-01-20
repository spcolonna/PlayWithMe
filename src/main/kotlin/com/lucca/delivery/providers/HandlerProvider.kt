package com.lucca.delivery.providers

import com.lucca.delivery.handler.SubscriberHandler
import com.lucca.delivery.presenter.SubscriberPresenter
import com.lucca.domain.useCase.subscriber.CreateSubscriberUseCase
import com.lucca.infra.service.IdGenerator

object HandlerProvider {
    private val idGenerator = IdGenerator()
    private val subscriberRepository = RepositoryProvider.subscriberRepo

    fun loaderHandler() = listOf(
        subscriberHandler()
    )

    private fun subscriberHandler() =
        SubscriberHandler(
            SubscriberPresenter(
                CreateSubscriberUseCase(
                    idGenerator,
                    subscriberRepository
                )
            )
        )
}