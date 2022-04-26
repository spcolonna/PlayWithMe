package domain.useCases.vendor

import domain.doubles.VendorRepositoryDouble
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import playwithyou.lucca.domain.useCase.vendor.DeleteVendorUseCase

class DeleteVendorTest {

    @Test
    fun `delete vendor`(){
        val repository = VendorRepositoryDouble()
        var useCase = DeleteVendorUseCase(repository)

        val vendorId = "vendorId"
        useCase.execute(vendorId)

        repository.wasCalled.shouldBeTrue()
        repository.lastVendorIdCalled.shouldBe(vendorId)
    }

    @Test
    fun `delete another vendor`(){
        val repository = VendorRepositoryDouble()
        var useCase = DeleteVendorUseCase(repository)

        val vendorId = "anotherVendorId"
        useCase.execute(vendorId)

        repository.wasCalled.shouldBeTrue()
        repository.lastVendorIdCalled.shouldBe(vendorId)
    }
}

