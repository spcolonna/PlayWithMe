package com.lucca.delivery.dto

data class CreatePlayerDto(
    val mail: String,
    val password: String,
    val name: String,
    val accountNumber: String
)