package domain.useCases.house

import domain.doubles.HouseRepositoryDouble
import io.kotest.matchers.shouldBe
import org.junit.Ignore
import org.junit.jupiter.api.Test
import playwithyou.lucca.domain.Given
import playwithyou.lucca.domain.entity.Coordinates
import playwithyou.lucca.domain.useCase.house.GetHousesAroundUseCase

class GetHousesAroundTest {

    @Ignore("test deprecated for around 3m")
    fun `get house around`() {
        val expected = Given.aHouse(coordinates = Coordinates(-34.903873, -56.152775))
        val myCoordinate = Given.aCoordinatesDto(-34.903222, -56.153998)
        val repository = HouseRepositoryDouble(listOf(expected))
        val useCase = GetHousesAroundUseCase(repository)

        val result = useCase.execute(myCoordinate)

        result.first().shouldBe(expected)
    }

    @Ignore("test deprecated for around 3m")
    fun `get another house around`() {
        val expected = Given.aHouse(coordinates = Coordinates(-35.903873, -54.152775))
        val myCoordinate = Given.aCoordinatesDto(-34.903222, -56.153998)
        val repository = HouseRepositoryDouble(listOf(expected))
        val useCase = GetHousesAroundUseCase(repository)

        val result = useCase.execute(myCoordinate)

        result.first().shouldBe(expected)
    }

    @Test
    fun `get only 3m house around`() {
        val expected = Given.aHouse(coordinates = Coordinates(-34.903873, -56.152775))
        val storedHouses = listOf(Given.aHouse(coordinates = Coordinates(37.421028, -122.084660)), expected)
        val myCoordinate = Given.aCoordinatesDto(-34.903222, -56.153998)
        val repository = HouseRepositoryDouble(storedHouses)
        val useCase = GetHousesAroundUseCase(repository)

        val result = useCase.execute(myCoordinate)

        result.first().shouldBe(expected)
    }
}