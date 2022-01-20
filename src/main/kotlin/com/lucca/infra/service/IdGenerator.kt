package com.lucca.infra.service

import com.lucca.domain.interace.IIdGenerator
import java.nio.ByteBuffer
import java.util.*

class IdGenerator : IIdGenerator {
    private val encoder = Base64.getUrlEncoder()
    private val bytesLength = 16
    private val randomLength = 22

    override fun getId(): String =
        encode(extractParts(generateGuid()))

    private fun generateGuid() = UUID.randomUUID()!!

    private fun encode(src: ByteArray) = encoder.encodeToString(src).substring(0, randomLength)

    private fun extractParts(uuid: UUID) = ByteBuffer
        .wrap(ByteArray(bytesLength))
        .putLong(uuid.mostSignificantBits)
        .putLong(uuid.leastSignificantBits)
        .array()

}