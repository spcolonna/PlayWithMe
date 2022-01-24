package com.lucca.domain.interace

import com.lucca.domain.entity.CreateEvent

interface IEventRepository {
    fun store(createEvent: CreateEvent)

}