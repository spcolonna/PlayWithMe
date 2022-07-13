package domain.useCases.seller

import domain.doubles.SellerRepositoryDouble
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import playwithyou.lucca.domain.useCase.seller.DeleteSellerUseCase

class DeleteSellerTest {

    @Test
    fun `delete seller`(){
        val repository = SellerRepositoryDouble()
        var useCase = DeleteSellerUseCase(repository)

        val sellerId = "sellerId"
        useCase.execute(sellerId)

        repository.wasCalled.shouldBeTrue()
        repository.lastVendorIdCalled.shouldBe(sellerId)
    }

    @Test
    fun `delete another seller`(){
        val repository = SellerRepositoryDouble()
        var useCase = DeleteSellerUseCase(repository)

        val sellerId = "anotherSellerId"
        useCase.execute(sellerId)

        repository.wasCalled.shouldBeTrue()
        repository.lastVendorIdCalled.shouldBe(sellerId)
    }
}

