package domain.vendor

import domain.doubles.IdGeneratorDouble
import domain.doubles.VendorRepositoryDouble
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import playwithyou.lucca.domain.Given
import playwithyou.lucca.domain.useCase.vendor.CreateVendorUseCase

class CreateVendorTest {

    @Test
    fun `create vendor`() {
        val expectedId = "vendorId"
        val expectedVendor = Given.aVendor(id = expectedId)

        val vendorDto = Given.aVendorDto()
        val repository = VendorRepositoryDouble()
        val idGenerator = IdGeneratorDouble(expectedId)
        val useCase = CreateVendorUseCase(idGenerator, repository)
        
        val result = useCase.execute(vendorDto)

        result.shouldBe(expectedId)
        repository.wasCalled.shouldBeTrue()
        repository.lastVendorStored.shouldBe(expectedVendor)
    }
}

