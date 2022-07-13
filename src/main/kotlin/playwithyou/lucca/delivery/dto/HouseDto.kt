package playwithyou.lucca.delivery.dto

import playwithyou.lucca.domain.entity.Coordinates

data class HouseDto(val coordinates: Coordinates, val houseDimension: Int, val address: String, val price: Int) {

}