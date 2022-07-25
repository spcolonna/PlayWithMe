package domain.useCases.seller

import domain.doubles.IdGeneratorDouble
import domain.doubles.SellerRepositoryDouble
import io.kotest.matchers.booleans.shouldBeFalse
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
    fun `Not validate seller mail if exist`(){
        val mail = "mail@mail.com"
        val sellerDto = Given.aSellerDto(mail = mail)
        val seller = Given.aSeller(mail = mail)
        val repository = SellerRepositoryDouble(seller)
        val useCase = CreateSellerUseCase(IdGeneratorDouble("id"),repository)

        val result = useCase.validateDto(sellerDto)

        result.shouldBeFalse()
    }

    @Test
    fun `Not validate another seller mail if exist`(){
        val mail = "mail2@mail.com"
        val sellerDto = Given.aSellerDto(mail = mail)
        val seller = Given.aSeller(mail = mail)
        val repository = SellerRepositoryDouble(seller)
        val useCase = CreateSellerUseCase(IdGeneratorDouble("id"),repository)

        val result = useCase.validateDto(sellerDto)

        result.shouldBeFalse()
    }

    @Test
    fun `Validate seller mail if exist`(){
        val mail = "mail@mail.com"
        val sellerDto = Given.aSellerDto(mail = mail)
        val repository = SellerRepositoryDouble()
        val useCase = CreateSellerUseCase(IdGeneratorDouble("id"),repository)

        val result = useCase.validateDto(sellerDto)

        result.shouldBeTrue()
    }

}

