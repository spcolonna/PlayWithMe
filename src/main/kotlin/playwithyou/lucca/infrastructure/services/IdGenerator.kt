package playwithyou.lucca.infrastructure.services

import playwithyou.lucca.domain.interfaces.IIdGenerator
import java.util.*

class IdGenerator : IIdGenerator {

    override fun generate() = UUID.randomUUID().toString()
}