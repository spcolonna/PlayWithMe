package com.lucca.delivery.dto

import com.lucca.domain.enums.EventStates

class UpdateEventDto(
    val playerId: String,
    val subscriberId: String,
    val date: String,
    val reservationConfirm: Boolean,
    val state: EventStates
)