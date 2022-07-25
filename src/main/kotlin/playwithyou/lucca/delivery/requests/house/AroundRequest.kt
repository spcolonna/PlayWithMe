package playwithyou.lucca.delivery.requests.house

import kotlinx.serialization.Serializable
import playwithyou.lucca.domain.entity.House

@Serializable
data class AroundRequest(val houses: List<House>){

}
