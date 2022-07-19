package playwithyou.lucca.domain.useCase.house

import playwithyou.lucca.delivery.dto.HouseDto
import playwithyou.lucca.domain.Builder
import playwithyou.lucca.domain.entity.House
import playwithyou.lucca.domain.interfaces.IHouseRepositoryDouble
import playwithyou.lucca.domain.interfaces.IIdGenerator
import playwithyou.lucca.domain.interfaces.ISellerRepository

class CreateHouse(
    private val houseRepository: IHouseRepositoryDouble,
    private val sellerRepository: ISellerRepository,
    private val idGenerator: IIdGenerator
) {
    fun execute(dto: HouseDto): String {
        if (validate(dto)) return storeHouse(dto).houseId
        return ""
    }

    private fun storeHouse(dto: HouseDto): House {
        val id = idGenerator.generate()
        val house = Builder.createHouseFromDto(id, dto)
        houseRepository.store(house)
        return house
    }

    fun validate(dto: HouseDto) = sellerRepository.get(dto.sellerId) != null

}