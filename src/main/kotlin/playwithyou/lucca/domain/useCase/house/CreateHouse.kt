package playwithyou.lucca.domain.useCase.house

import playwithyou.lucca.delivery.dto.HouseDto
import playwithyou.lucca.domain.Builder
import playwithyou.lucca.domain.interfaces.IHouseRepositoryDouble
import playwithyou.lucca.domain.interfaces.IIdGenerator

class CreateHouse(private val repository: IHouseRepositoryDouble, private val idGenerator: IIdGenerator) {
    fun execute(dto: HouseDto): String {
        val id = idGenerator.generate()
        val house = Builder.createHouseFromDto(id, dto)
        repository.store(house)
        return id
    }

}