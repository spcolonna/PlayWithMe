package playwithyou.lucca.delivery.requests

import kotlinx.serialization.Serializable
import playwithyou.lucca.delivery.dto.SellerDto

@Serializable
data class CreateSellerRequest(val name: String, val phone: String, val mail: String) {

    fun toDto() = SellerDto(name, phone, mail)
}