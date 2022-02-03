package com.lucca.domain.entity

import com.lucca.domain.enums.DaysEnum

data class Scheduler(
    val scheduleId: String,
    val playerId: String,
    val availableDays: List<DaysEnum>,
    val availableHours: List<Int>
)