package playwithyou.lucca.domain

import playwithyou.lucca.delivery.dto.VendorDto
import playwithyou.lucca.domain.entity.Seller

class Builder {
    companion object{
        fun createVendorFromDto(id: String, dto: VendorDto) = Seller(id, dto.name, dto.phone, dto.mail, true)

    }
}