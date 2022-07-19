package playwithyou.lucca.domain

import playwithyou.lucca.delivery.dto.HouseDto
import playwithyou.lucca.delivery.dto.VendorDto
import playwithyou.lucca.domain.entity.House
import playwithyou.lucca.domain.entity.Seller

class Builder {
    companion object{
        fun createSellerFromDto(id: String, dto: VendorDto) = Seller(id, dto.name, dto.phone, dto.mail, true)
        fun createHouseFromDto(id: String, dto: HouseDto) = House(
            id,
            dto.sellerId,
            dto.coordinates,
            dto.houseDimension,
            dto.address,
            dto.price
        )

    }
}