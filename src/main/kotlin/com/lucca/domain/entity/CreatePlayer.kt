package com.lucca.domain.entity

data class CreatePlayer(
    val id: String,
    val mail: String,
    val password: String,
    val name: String,
    val accountNumber: String,
    val listOf: List<PlayEvent>)