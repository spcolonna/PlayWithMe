package playwithyou.lucca.infrastructure.services

import playwithyou.lucca.domain.interfaces.IIdGenerator
import java.util.*

class IdGenerator : IIdGenerator {

    override fun generate(): String {
        return UUID.randomUUID().toString()
    }
}