package domain.useCases.house

import domain.doubles.HouseRepositoryDouble
import domain.doubles.IdGeneratorDouble
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import playwithyou.lucca.domain.Given
import playwithyou.lucca.domain.entity.Coordinates
import playwithyou.lucca.domain.useCase.house.CreateHouse

class CreateHouseTest {

    @Test
    fun `create house`() {
        val houseId = "houseId"
        val expectedHouse = Given.aHouse(houseId)

        val dto = Given.aHouseDto()
        val idGenerator = IdGeneratorDouble(houseId)
        val repository = HouseRepositoryDouble()
        val useCase = CreateHouse(repository, idGenerator)

        val result = useCase.execute(dto)

        result.shouldBe(houseId)
        repository.lastHouseCalled.shouldBe(expectedHouse)
    }

    @Test
    fun `create another house`() {
        val houseId = "anotherHouseId"
        val expectedHouse = Given.aHouse(houseId)

        val dto = Given.aHouseDto()
        val idGenerator = IdGeneratorDouble(houseId)
        val repository = HouseRepositoryDouble()
        val useCase = CreateHouse(repository, idGenerator)

        val result = useCase.execute(dto)

        result.shouldBe(houseId)
        repository.lastHouseCalled.shouldBe(expectedHouse)
    }

    @Test
    fun `create house with data`(){
        val coordinates = Coordinates(1.0, 1.0)
        val houseId = "id"
        val expectedHouse = Given.aHouse(houseId, coordinates, 25, "address", 89000)

        val dto = Given.aHouseDto(coordinates, 25, "address", 89000)
        val idGenerator = IdGeneratorDouble(houseId)
        val repository = HouseRepositoryDouble()
        val useCase = CreateHouse(repository, idGenerator)

        val result = useCase.execute(dto)

        result.shouldBe(houseId)
        repository.lastHouseCalled.shouldBe(expectedHouse)
    }

}

