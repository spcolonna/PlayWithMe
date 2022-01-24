package com.lucca.domain.entity

data class CreateEvent(
    val eventId: String,
    val playerId: String,
    val subscriberId: String,
    val date: String,
    val reservationConfirm: Boolean
)