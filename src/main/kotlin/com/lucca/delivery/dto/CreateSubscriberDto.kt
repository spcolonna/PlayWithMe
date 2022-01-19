package com.lucca.delivery.dto

data class CreateSubscriberDto(
    val mail: String,
    val name: String,
    val birthDate: String
)