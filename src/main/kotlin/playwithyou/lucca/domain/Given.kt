package playwithyou.lucca.domain

import playwithyou.lucca.delivery.dto.VendorDto
import playwithyou.lucca.domain.entity.Vendor

class Given {
    companion object {
        fun aVendorDto(
            name: String = "name",
            phone: String = "phone",
            mail: String = "mail"
        ) = VendorDto(name, phone, mail)

        fun aVendor(
            id: String = "id",
            name: String = "name",
            phone: String = "phone",
            mail: String = "mail",
            activate: Boolean = true
        ) = Vendor(id, name, phone, mail, activate)
    }
}