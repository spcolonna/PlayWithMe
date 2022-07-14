package playwithyou.lucca.domain

import playwithyou.lucca.delivery.dto.HouseDto
import playwithyou.lucca.delivery.dto.VendorDto
import playwithyou.lucca.domain.entity.Coordinates
import playwithyou.lucca.domain.entity.House
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

        fun aHouseDto(
            coordinates: Coordinates = Coordinates(0.0, 0.0),
            houseDimension: Int = 0,
            address: String = "En un lugar de la mancha",
            price: Int = 0
        ) = HouseDto(coordinates, houseDimension, address, price)

        fun aHouse(
            houseId: String = "id",
            coordinates: Coordinates = Coordinates(0.0, 0.0),
            houseDimension: Int = 0,
            address: String = "En un lugar de la mancha",
            price: Int = 0
        ) = House(houseId, coordinates, houseDimension, address, price)
    }
}

