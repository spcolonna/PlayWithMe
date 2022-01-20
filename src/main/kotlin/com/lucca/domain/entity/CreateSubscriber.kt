package com.lucca.domain.entity

data class CreateSubscriber(
    val id: String,
    val mail: String,
    val password: String,
    val name: String,
    val birthDate: String
)