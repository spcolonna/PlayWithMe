package playwithyou.lucca.domain.useCase.house

import playwithyou.lucca.delivery.dto.CoordinateDto
import playwithyou.lucca.domain.entity.House
import playwithyou.lucca.domain.interfaces.IHouseRepository

class GetHousesAroundUseCase(private val repository: IHouseRepository) {
    companion object {
        const val AROUND_METERS = 3
        const val LATITUDE_EQ_ONE_METERS = 0.003
        const val LONGITUDE_EQ_ONE_METERS = 0.006
    }

    fun execute(dto: CoordinateDto): List<House> {
        val maxLatitude = dto.latitude + (LATITUDE_EQ_ONE_METERS * AROUND_METERS)
        val minLatitude = dto.latitude - (LATITUDE_EQ_ONE_METERS * AROUND_METERS)
        val maxLongitude = dto.longitude + (LONGITUDE_EQ_ONE_METERS * AROUND_METERS)
        val minLongitude = dto.longitude - (LONGITUDE_EQ_ONE_METERS * AROUND_METERS)
        return repository.getHousesWithin(maxLatitude, minLatitude, maxLongitude, minLongitude)
    }

}
