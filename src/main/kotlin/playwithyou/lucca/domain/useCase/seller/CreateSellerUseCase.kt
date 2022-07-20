package playwithyou.lucca.domain.useCase.seller

import playwithyou.lucca.delivery.dto.SellerDto
import playwithyou.lucca.domain.Builder
import playwithyou.lucca.domain.interfaces.IIdGenerator
import playwithyou.lucca.domain.interfaces.ISellerRepository

class CreateSellerUseCase(private val idGenerator: IIdGenerator, private val repository: ISellerRepository) {

    fun execute(dto: SellerDto): String {
        val id = idGenerator.generate()
        val seller = Builder.createSellerFromDto(id, dto)
        repository.store(seller)
        return id
    }

}