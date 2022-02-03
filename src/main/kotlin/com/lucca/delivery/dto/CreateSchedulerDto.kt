package com.lucca.delivery.dto

import com.lucca.domain.enums.DaysEnum

class CreateSchedulerDto(val playerId: String, val availableDays: List<DaysEnum>, val availableHours: List<Int>)