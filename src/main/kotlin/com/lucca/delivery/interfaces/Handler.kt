package com.lucca.delivery.interfaces

import io.ktor.application.*

interface Handler{
    fun routing(a: Application)
}
