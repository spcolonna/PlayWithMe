package playwithyou.lucca.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class House(
    val id: String,
    var sellerId: String,
    val coordinates: Coordinates,
    val houseDimension: Int,
    val address: String,
    val price: Int
) {

}