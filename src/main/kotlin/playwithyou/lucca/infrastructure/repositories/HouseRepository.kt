package playwithyou.lucca.infrastructure.repositories

import com.mongodb.MongoException
import com.mongodb.client.MongoCollection
import org.bson.Document
import playwithyou.lucca.domain.entity.House
import playwithyou.lucca.domain.interfaces.IHouseRepository

class HouseRepository : IHouseRepository {

    override fun store(house: House)  = try {
        val collection = getCollection()
        val document = createDocument(house)
        collection.insertOne(document)
    } catch (e: MongoException) {
        e.printStackTrace()
    }

    override fun getHousesWithin(
        maxLatitude: Double,
        minLatitude: Double,
        maxLongitude: Double,
        minLongitude: Double
    ): List<House> {
        TODO("Not yet implemented")
    }

    private fun createDocument(house: House): Document {
        val document = Document()
        document["id"] = house.id
        document["sellerId"] = house.sellerId
        document["latitude"] = house.coordinates.latitude
        document["longitude"] = house.coordinates.longitude
        document["houseDimension"] = house.houseDimension
        document["address"] = house.address
        document["price"] = house.price
        return document
    }

    private fun getCollection(): MongoCollection<Document> {
        var database = getDataBase()!!
        return database.getCollection("House")
    }

    private fun getDataBase() = MongoClientSingleton.mongoClient?.getDatabase("TinderHouse")

}
