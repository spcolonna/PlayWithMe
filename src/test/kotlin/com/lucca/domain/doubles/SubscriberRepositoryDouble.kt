package com.lucca.domain.doubles

import com.lucca.domain.entity.CreateSubscriber
import com.lucca.domain.interace.ISubscriberRepository

class SubscriberRepositoryDouble : ISubscriberRepository {
    lateinit var lastStoredSubscriber: CreateSubscriber
    var wasCalled: Boolean = false
    override fun store(createSubscriber: CreateSubscriber) {
        wasCalled = true
        lastStoredSubscriber = createSubscriber
    }
}