package com.lucca.delivery

import io.ktor.application.*

interface Handler{
    fun routing(a: Application)
}
