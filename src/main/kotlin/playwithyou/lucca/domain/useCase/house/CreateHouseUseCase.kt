package playwithyou.lucca.domain.useCase.house

import playwithyou.lucca.delivery.dto.HouseDto
import playwithyou.lucca.domain.Builder
import playwithyou.lucca.domain.entity.House
import playwithyou.lucca.domain.interfaces.IHouseRepository
import playwithyou.lucca.domain.interfaces.IIdGenerator
import playwithyou.lucca.domain.interfaces.ISellerRepository

class CreateHouseUseCase(
    private val houseRepository: IHouseRepository,
    private val sellerRepository: ISellerRepository,
    private val idGenerator: IIdGenerator
) {
    fun execute(dto: HouseDto): String {
        if (validateDto(dto)) return storeHouse(dto).id
        return ""
    }

    private fun storeHouse(dto: HouseDto): House {
        val id = idGenerator.generate()
        val house = Builder.createHouseFromDto(id, dto)
        houseRepository.store(house)
        return house
    }

    fun validateDto(dto: HouseDto) = sellerRepository.get(dto.sellerId) != null

}