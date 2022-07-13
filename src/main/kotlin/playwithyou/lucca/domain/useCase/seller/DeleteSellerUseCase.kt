package playwithyou.lucca.domain.useCase.seller

import playwithyou.lucca.domain.interfaces.ISellerRepository

class DeleteSellerUseCase(private val repository: ISellerRepository) {
    fun execute(vendorId: String) = repository.delete(vendorId)

}