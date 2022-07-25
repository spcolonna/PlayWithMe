package playwithyou.lucca.delivery.requests.house

import kotlinx.serialization.Serializable
import playwithyou.lucca.delivery.dto.CoordinateDto

@Serializable
data class HouseAroundRequest(val latitude: Double, val longitude: Double){
    fun toDto() = CoordinateDto(latitude, longitude)
}
