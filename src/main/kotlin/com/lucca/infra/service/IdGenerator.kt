package com.lucca.infra.service

import com.lucca.domain.interace.IIdGenerator

class IdGenerator : IIdGenerator {
    override fun getId(): String {
        return "id"
    }

}