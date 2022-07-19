package playwithyou.lucca.domain.interfaces

import playwithyou.lucca.domain.entity.House

interface IHouseRepositoryDouble {
    fun store(house: House)
    fun getHousesWithin(
        maxLatitude: Double,
        minLatitude: Double,
        maxLongitude: Double,
        minLongitude: Double
    ): List<House>

}