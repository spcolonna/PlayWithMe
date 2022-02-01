package com.lucca.domain.entity

import com.lucca.domain.enums.EventStates

data class Event(
    val eventId: String,
    val playerId: String,
    val subscriberId: String,
    val date: String,
    val reservationConfirm: Boolean,
    val state: EventStates
)