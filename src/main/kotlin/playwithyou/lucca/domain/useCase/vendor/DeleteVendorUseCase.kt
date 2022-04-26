package playwithyou.lucca.domain.useCase.vendor

import playwithyou.lucca.domain.interfaces.IVendorRepository

class DeleteVendorUseCase(val repository: IVendorRepository) {
    fun execute(vendorId: String) {
        repository.delete(vendorId)
    }

}