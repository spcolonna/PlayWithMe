package domain.doubles

import playwithyou.lucca.domain.entity.House
import playwithyou.lucca.domain.interfaces.IHouseRepositoryDouble

class HouseRepositoryDouble : IHouseRepositoryDouble {

    lateinit var lastHouseCalled: House
    override fun store(house: House) {
        lastHouseCalled = house
    }
}