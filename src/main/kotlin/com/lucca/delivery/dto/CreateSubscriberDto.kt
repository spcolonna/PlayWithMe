package com.lucca.delivery.dto

data class CreateSubscriberDto(
    val mail: String,
    val password: String,
    val name: String,
    val birthDate: String
)