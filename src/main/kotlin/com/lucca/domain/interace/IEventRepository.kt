package com.lucca.domain.interace

import com.lucca.domain.entity.CreateEvent
import com.lucca.domain.entity.CreateSubscriber

interface IEventRepository {
    fun store(createEvent: CreateEvent)

}