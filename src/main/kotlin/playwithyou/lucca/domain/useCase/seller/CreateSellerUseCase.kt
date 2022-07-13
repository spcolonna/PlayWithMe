package playwithyou.lucca.domain.useCase.seller

import playwithyou.lucca.delivery.dto.VendorDto
import playwithyou.lucca.domain.Builder
import playwithyou.lucca.domain.interfaces.IIdGenerator
import playwithyou.lucca.domain.interfaces.ISellerRepository

class CreateSellerUseCase(private val idGenerator: IIdGenerator, private val repository: ISellerRepository) {

    fun execute(dto: VendorDto): String {
        val id = idGenerator.generate()
        val vendor = Builder.createVendorFromDto(id, dto)
        repository.store(vendor)
        return id
    }

}