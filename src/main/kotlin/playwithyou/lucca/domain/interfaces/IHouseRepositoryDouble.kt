package playwithyou.lucca.domain.interfaces

import playwithyou.lucca.domain.entity.House

interface IHouseRepositoryDouble {
    fun store(house: House)
    fun getHousesWithin(latitude: Double, longitude: Double, leftBorder: Double, rightBorder: Double): List<House>

}