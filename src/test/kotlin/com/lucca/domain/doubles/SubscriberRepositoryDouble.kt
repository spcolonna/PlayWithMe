package com.lucca.domain.doubles

import com.lucca.domain.entity.CreateSubscriber
import com.lucca.domain.interace.ISubscriberRepository

class SubscriberRepositoryDouble : ISubscriberRepository {
    lateinit var lastStoredSubscriber: CreateSubscriber
    private var storedSubscribers = listOf<CreateSubscriber>()
    var wasCalled: Boolean = false


    override fun store(createSubscriber: CreateSubscriber) {
        wasCalled = true
        lastStoredSubscriber = createSubscriber
    }

    override fun isMailAvailable(mail: String) = !storedSubscribers.any { it.mail == mail }

    fun setStoredSubscribers(subscribers: List<CreateSubscriber>) {
        storedSubscribers = subscribers
    }
}