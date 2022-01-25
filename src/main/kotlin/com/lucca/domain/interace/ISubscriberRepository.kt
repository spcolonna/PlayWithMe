package com.lucca.domain.interace

import com.lucca.domain.entity.CreateSubscriber

interface ISubscriberRepository {
    fun store(createSubscriber: CreateSubscriber)
    fun isMailAvailable(mail: String): Boolean
    fun isSubscriberValid(subscriberId: String): Boolean

}