package com.lucca.delivery.request

import com.lucca.delivery.dto.CreateSubscriberDto
import kotlinx.serialization.Serializable

@Serializable
class CreateSubscriberRequest(
    private val mail: String,
    private val password: String,
    private val name: String,
    private val birthDate: String
) {
    fun toDto() = CreateSubscriberDto(mail, password, name, birthDate)
}