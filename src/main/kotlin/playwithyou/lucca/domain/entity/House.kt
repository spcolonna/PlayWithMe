package playwithyou.lucca.domain.entity

data class House(
    val houseId: String,
    val coordinates: Coordinates,
    val houseDimension: Int,
    val address: String,
    val price: Int
) {

}