package playwithyou.lucca.delivery.requests.house

import kotlinx.serialization.Serializable
import playwithyou.lucca.delivery.dto.HouseDto
import playwithyou.lucca.domain.entity.Coordinates

@Serializable
data class CreateHouseRequest(
    private val sellerId: String,
    private val coordinates: Coordinates,
    private val houseDimension: Int,
    private val address: String,
    private val price: Int
) {
    fun toDto() = HouseDto(sellerId, coordinates, houseDimension, address, price)
}
