package com.lucca.domain.interace

import com.lucca.domain.entity.Scheduler

interface ISchedulerRepository {
    fun store(scheduler: Scheduler)

}