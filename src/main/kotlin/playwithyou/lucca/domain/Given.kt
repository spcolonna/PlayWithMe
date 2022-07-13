package playwithyou.lucca.domain

import playwithyou.lucca.delivery.dto.VendorDto
import playwithyou.lucca.domain.entity.Seller

class Given {
    companion object {
        fun aSellerDto(
            name: String = "name",
            phone: String = "phone",
            mail: String = "mail"
        ) = VendorDto(name, phone, mail)

        fun aSeller(
            id: String = "id",
            name: String = "name",
            phone: String = "phone",
            mail: String = "mail",
            activate: Boolean = true
        ) = Seller(id, name, phone, mail, activate)
    }
}