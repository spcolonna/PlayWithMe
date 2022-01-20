package com.lucca.domain

import com.lucca.delivery.dto.CreateSubscriberDto
import com.lucca.domain.entity.CreateSubscriber

class Builder {
    companion object {
        fun createSubscriberFromDto(id: String, dto: CreateSubscriberDto): CreateSubscriber {
            return CreateSubscriber(id, dto.mail, dto.password, dto.name, dto.birthDate)
        }
    }
}