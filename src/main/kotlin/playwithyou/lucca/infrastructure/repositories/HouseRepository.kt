package playwithyou.lucca.infrastructure.repositories

import com.mongodb.MongoException
import com.mongodb.client.MongoCollection
import org.bson.Document
import playwithyou.lucca.domain.entity.Coordinates
import playwithyou.lucca.domain.entity.House
import playwithyou.lucca.domain.interfaces.IHouseRepository

class HouseRepository : IHouseRepository {

    override fun store(house: House) = try {
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
        val collection = getCollection()
        var documents = collection.find(
            Document("latitude", Document("\$gte", minLatitude))
                .append("latitude", Document("\$lte", maxLatitude))
                .append("longitude", Document("\$gte", minLongitude))
                .append("longitude", Document("\$lte", maxLongitude))
        )
        return documents.map { createHouse(it) }.toList()
    }

    private fun createHouse(document: Document): House {
        return House(
            document.getString("id"),
            document.getString("sellerId"),
            Coordinates(document.getDouble("latitude"), document.getDouble("longitude")),
            document.getInteger("houseDimension"),
            document.getString("address"),
            document.getInteger("price")
        )
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
