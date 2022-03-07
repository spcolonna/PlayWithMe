package domain.doubles

import playwithyou.lucca.domain.interfaces.IIdGenerator

class IdGeneratorDouble(private val id: String) : IIdGenerator {
    override fun generate() = id

}