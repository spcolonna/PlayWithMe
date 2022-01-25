package com.lucca.domain.doubles

import com.lucca.domain.entity.CreateSubscriber
import com.lucca.domain.interace.ISubscriberRepository

class SubscriberRepositoryDouble(private val storedSubscribers: List<CreateSubscriber> = listOf()) : ISubscriberRepository {
    lateinit var lastStoredSubscriber: CreateSubscriber
    var wasCalled: Boolean = false


    override fun store(createSubscriber: CreateSubscriber) {
        wasCalled = true
        lastStoredSubscriber = createSubscriber
    }

    override fun isMailAvailable(mail: String): Boolean {
        wasCalled = true
        return !storedSubscribers.any { it.mail == mail }
    }

    override fun isSubscriberValid(subscriberId: String): Boolean {
        wasCalled = true
        return storedSubscribers.any { it.id == subscriberId }
    }
}