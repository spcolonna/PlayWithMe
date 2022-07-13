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
        var houseId = "houseId"
        var expectedHouse = Given.aHouse(houseId)

        var dto = Given.aHouseDto()
        var idGenerator = IdGeneratorDouble(houseId)
        var repository = HouseRepositoryDouble()
        var useCase = CreateHouse(repository, idGenerator)

        var result = useCase.execute(dto)

        result.shouldBe(houseId)
        repository.lastHouseCalled.shouldBe(expectedHouse)
    }

    @Test
    fun `create another house`() {
        var houseId = "anotherHouseId"
        var expectedHouse = Given.aHouse(houseId)

        var dto = Given.aHouseDto()
        var idGenerator = IdGeneratorDouble(houseId)
        var repository = HouseRepositoryDouble()
        var useCase = CreateHouse(repository, idGenerator)

        var result = useCase.execute(dto)

        result.shouldBe(houseId)
        repository.lastHouseCalled.shouldBe(expectedHouse)
    }

    @Test
    fun `create house with data`(){
        var coordinates = Coordinates(1f,1f)
        val houseId = "id"
        var expectedHouse = Given.aHouse(houseId, coordinates, 25, "address", 89000)

        var dto = Given.aHouseDto(coordinates, 25, "address", 89000)
        var idGenerator = IdGeneratorDouble(houseId)
        var repository = HouseRepositoryDouble()
        var useCase = CreateHouse(repository, idGenerator)

        var result = useCase.execute(dto)

        result.shouldBe(houseId)
        repository.lastHouseCalled.shouldBe(expectedHouse)
    }

}

