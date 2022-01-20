package com.lucca.domain.doubles

import com.lucca.domain.interace.IIdGenerator

class IdGeneratorDouble(private val id: String = "") : IIdGenerator {
    override fun getId() = id

}