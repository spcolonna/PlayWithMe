package playwithyou.lucca.domain.useCase.vendor

import playwithyou.lucca.delivery.dto.VendorDto
import playwithyou.lucca.domain.Builder
import playwithyou.lucca.domain.interfaces.IIdGenerator
import playwithyou.lucca.domain.interfaces.IVendorRepository

class CreateVendorUseCase(private val idGenerator: IIdGenerator, private val repository: IVendorRepository) {

    fun execute(dto: VendorDto): String {
        val id = idGenerator.generate()
        val vendor = Builder.createVendorFromDto(id, dto)
        repository.store(vendor)
        return id
    }

}