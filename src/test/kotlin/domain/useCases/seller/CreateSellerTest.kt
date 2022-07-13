package domain.useCases.seller

import domain.doubles.IdGeneratorDouble
import domain.doubles.SellerRepositoryDouble
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import playwithyou.lucca.domain.Given
import playwithyou.lucca.domain.useCase.seller.CreateSellerUseCase

class CreateSellerTest {

    @Test
    fun `create seller`() {
        val expectedId = "sellerId"
        val expectedSeller = Given.aSeller(id = expectedId)

        val sellerDto = Given.aSellerDto()
        val repository = SellerRepositoryDouble()
        val idGenerator = IdGeneratorDouble(expectedId)
        val useCase = CreateSellerUseCase(idGenerator, repository)

        val result = useCase.execute(sellerDto)

        result.shouldBe(expectedId)
        repository.wasCalled.shouldBeTrue()
        repository.lastVendorStored.shouldBe(expectedSeller)
    }

    @Test
    fun `create another seller`() {
        val expectedId = "anotherSellerId"
        val expectedSeller = Given.aSeller(id = expectedId)

        val vendorDto = Given.aSellerDto()
        val repository = SellerRepositoryDouble()
        val idGenerator = IdGeneratorDouble(expectedId)
        val useCase = CreateSellerUseCase(idGenerator, repository)

        val result = useCase.execute(vendorDto)

        result.shouldBe(expectedId)
        repository.wasCalled.shouldBeTrue()
        repository.lastVendorStored.shouldBe(expectedSeller)
    }
}

