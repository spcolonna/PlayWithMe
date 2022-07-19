package playwithyou.lucca.domain.useCase.house

import playwithyou.lucca.domain.entity.Coordinates
import playwithyou.lucca.domain.entity.House
import playwithyou.lucca.domain.interfaces.IHouseRepositoryDouble

class GetHousesAround(private val repository: IHouseRepositoryDouble) {
    companion object {
        const val AROUND_METERS = 3
        const val LATITUDE_EQ_ONE_METERS = 0.003
        const val LONGITUDE_EQ_ONE_METERS = 0.006
    }

    fun execute(myCoordinate: Coordinates): List<House> {
        val maxLatitude = myCoordinate.latitude + (LATITUDE_EQ_ONE_METERS * AROUND_METERS)
        val minLatitude = myCoordinate.latitude - (LATITUDE_EQ_ONE_METERS * AROUND_METERS)
        val maxLongitude = myCoordinate.longitude + (LONGITUDE_EQ_ONE_METERS * AROUND_METERS)
        val minLongitude = myCoordinate.longitude - (LONGITUDE_EQ_ONE_METERS * AROUND_METERS)
        return repository.getHousesWithin(maxLatitude, minLatitude, maxLongitude, minLongitude)
    }

}
