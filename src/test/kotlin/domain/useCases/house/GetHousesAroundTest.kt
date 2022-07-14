package domain.useCases.house

import domain.doubles.HouseRepositoryDouble
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import playwithyou.lucca.domain.Given
import playwithyou.lucca.domain.entity.Coordinates
import playwithyou.lucca.domain.entity.House
import playwithyou.lucca.domain.interfaces.IHouseRepositoryDouble

class GetHousesAroundTest {

    @Test
    fun `get house around`() {
        val expected = Given.aHouse(coordinates = Coordinates(-34.903873, -56.152775))
        val myCoordinate = Coordinates(-34.903222, -56.153998)
        val repository = HouseRepositoryDouble(listOf(expected))
        val useCase = GetHousesAround(repository)

        val result = useCase.execute(myCoordinate)

        result.first().shouldBe(expected)
    }

    @Test
    fun `get another house around`() {
        val expected = Given.aHouse(coordinates = Coordinates(-35.903873, -54.152775))
        val myCoordinate = Coordinates(-34.903222, -56.153998)
        val repository = HouseRepositoryDouble(listOf(expected))
        val useCase = GetHousesAround(repository)

        val result = useCase.execute(myCoordinate)

        result.first().shouldBe(expected)
    }

    @Test
    fun `get only 3m house around`() {
        val expected = Given.aHouse(coordinates = Coordinates(-34.903873, -56.152775))
        val storedHouses = listOf(Given.aHouse(coordinates = Coordinates(37.421028, -122.084660)), expected)
        val myCoordinate = Coordinates(-34.903222, -56.153998)
        val repository = HouseRepositoryDouble(storedHouses)
        val useCase = GetHousesAround(repository)

        val result = useCase.execute(myCoordinate)

        result.first().shouldBe(expected)
    }
}

class GetHousesAround(private val repository: IHouseRepositoryDouble) {
    companion object {
        const val AROUND_METERS = 3
        const val LATITUDE_EQ_ONE_METERS = 0.003
        const val LONGITUDE_EQ_ONE_METERS = 0.006
    }

    fun execute(myCoordinate: Coordinates): List<House> {
        val maxLatitude = myCoordinate.latitude + (LATITUDE_EQ_ONE_METERS * AROUND_METERS)
        val minLatitude = myCoordinate.latitude - (LATITUDE_EQ_ONE_METERS * AROUND_METERS)
        val maxLongitude = myCoordinate.longitude - (LONGITUDE_EQ_ONE_METERS * AROUND_METERS)
        val minLongitude = myCoordinate.longitude + (LONGITUDE_EQ_ONE_METERS * AROUND_METERS)
        return repository.getHousesWithin(maxLatitude, minLatitude, maxLongitude, minLongitude)
    }

}
