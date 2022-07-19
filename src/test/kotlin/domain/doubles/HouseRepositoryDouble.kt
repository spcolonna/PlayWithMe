package domain.doubles

import playwithyou.lucca.domain.entity.House
import playwithyou.lucca.domain.interfaces.IHouseRepositoryDouble

class HouseRepositoryDouble(houses: List<House> = listOf()) : IHouseRepositoryDouble {

    lateinit var lastHouseCalled: House
    private var storedHouses = houses
    override fun store(house: House) {
        lastHouseCalled = house
    }

    override fun getHousesWithin(
        maxLatitude: Double,
        minLatitude: Double,
        maxLongitude: Double,
        minLongitude: Double
    ) = storedHouses.filter { it.coordinates.latitude in minLatitude..maxLatitude && it.coordinates.longitude in minLongitude..maxLatitude }
}