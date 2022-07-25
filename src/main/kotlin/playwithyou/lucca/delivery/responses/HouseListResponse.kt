package playwithyou.lucca.delivery.responses

import kotlinx.serialization.Serializable
import playwithyou.lucca.domain.entity.House

@Serializable
class HouseListResponse(val houses: List<House>) {

}