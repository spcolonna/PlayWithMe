package domain.useCases.house

import domain.doubles.HouseRepositoryDouble
import domain.doubles.IdGeneratorDouble
import domain.doubles.SellerRepositoryDouble
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import playwithyou.lucca.domain.Given
import playwithyou.lucca.domain.entity.Coordinates
import playwithyou.lucca.domain.useCase.house.CreateHouse

class CreateHouseTest {

    @Test
    fun `create house`() {
        val houseId = "houseId"
        val seller = Given.aSeller()
        val expectedHouse = Given.aHouse(houseId, sellerId = seller.id)
        val dto = Given.aHouseDto(sellerId = seller.id)
        val idGenerator = IdGeneratorDouble(houseId)
        val repository = HouseRepositoryDouble()
        val useCase = CreateHouse(repository, SellerRepositoryDouble(seller), idGenerator)

        val result = useCase.execute(dto)

        result.shouldBe(houseId)
        repository.lastHouseCalled.shouldBe(expectedHouse)
    }

    @Test
    fun `create another house`() {
        val houseId = "anotherHouseId"
        val seller = Given.aSeller()
        val expectedHouse = Given.aHouse(houseId, sellerId = seller.id)
        val dto = Given.aHouseDto(sellerId = seller.id)
        val idGenerator = IdGeneratorDouble(houseId)
        val repository = HouseRepositoryDouble()
        val useCase = CreateHouse(repository, SellerRepositoryDouble(seller), idGenerator)

        val result = useCase.execute(dto)

        result.shouldBe(houseId)
        repository.lastHouseCalled.shouldBe(expectedHouse)
    }

    @Test
    fun `create house with data`() {
        val coordinates = Coordinates(1.0, 1.0)
        val houseId = "id"
        val seller = Given.aSeller()
        val expectedHouse = Given.aHouse(houseId, coordinates, 25, "address", 89000, sellerId = seller.id)

        val dto = Given.aHouseDto(coordinates, 25, "address", 89000, sellerId = seller.id)
        val idGenerator = IdGeneratorDouble(houseId)
        val repository = HouseRepositoryDouble()
        val useCase = CreateHouse(repository, SellerRepositoryDouble(seller), idGenerator)

        val result = useCase.execute(dto)

        result.shouldBe(houseId)
        repository.lastHouseCalled.shouldBe(expectedHouse)
    }

    @Test
    fun `validate exist seller id`() {
        val sellerId = "sellerId"
        val dto = Given.aHouseDto(sellerId = sellerId)
        val seller = Given.aSeller(sellerId)
        val repository = SellerRepositoryDouble(seller)
        val useCase = CreateHouse(HouseRepositoryDouble(), repository, IdGeneratorDouble("id"))

        val result = useCase.validate(dto)

        result.shouldBeTrue();
    }

    @Test
    fun `validate not exist seller id`() {
        val sellerId = "notExistSellerId"
        val dto = Given.aHouseDto(sellerId = sellerId)
        val useCase = CreateHouse(HouseRepositoryDouble(), SellerRepositoryDouble(), IdGeneratorDouble("id"))

        val result = useCase.validate(dto)

        result.shouldBeFalse();
    }

    @Test
    fun `not create house with not exist seller`() {
        val coordinates = Coordinates(1.0, 1.0)
        val dto = Given.aHouseDto(coordinates, 25, "address", 89000)
        val idGenerator = IdGeneratorDouble("id")
        val useCase = CreateHouse(HouseRepositoryDouble(), SellerRepositoryDouble(), idGenerator)

        val result = useCase.execute(dto)

        result.shouldBe("")
    }

}

