package domain.doubles

import playwithyou.lucca.domain.entity.House
import playwithyou.lucca.domain.interfaces.IHouseRepository

class HouseRepositoryDouble(houses: List<House> = listOf()) : IHouseRepository {

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