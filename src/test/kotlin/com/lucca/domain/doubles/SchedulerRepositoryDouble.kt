package com.lucca.domain.doubles

import com.lucca.domain.entity.Scheduler
import com.lucca.domain.interace.ISchedulerRepository

class SchedulerRepositoryDouble : ISchedulerRepository {

    lateinit var lastScheduleStored: Scheduler
    var wasCalled: Boolean = false
    override fun store(scheduler: Scheduler) {
        wasCalled = true
        lastScheduleStored = scheduler
    }
}