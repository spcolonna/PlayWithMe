package playwithyou.lucca.domain

import playwithyou.lucca.delivery.dto.VendorDto
import playwithyou.lucca.domain.entity.Vendor

class Builder {
    companion object{
        fun createVendorFromDto(id: String, dto: VendorDto) = Vendor(id, dto.name, dto.phone, dto.mail)

    }
}