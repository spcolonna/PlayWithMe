package playwithyou.lucca.infrastructure.repositories

import com.mongodb.BasicDBObject
import com.mongodb.MongoClient
import com.mongodb.MongoException
import org.bson.Document
import playwithyou.lucca.domain.entity.Seller
import playwithyou.lucca.domain.interfaces.ISellerRepository

class SellerRepository : ISellerRepository {

    override fun store(seller: Seller) {
        var mongoClient: MongoClient? = null
        try {
            mongoClient = MongoClient("127.0.0.1", 27017)
            var database = mongoClient.getDatabase("TinderHouse")
            var collection = database.getCollection("Seller")
            var document = Document()
            document["id"] = seller.id
            document["name"] = seller.name
            document["phone"] = seller.phone
            document["mail"] = seller.mail

            collection.insertOne(document)
            println("Kotlin connected to MongoDB! -> ${seller.name}")
        } catch (e: MongoException) {
            e.printStackTrace()
        } finally {
            mongoClient!!.close()
        }
    }

    override fun delete(sellerId: String) {
        TODO("Not yet implemented")
    }

    override fun get(id: String): Seller? {
        TODO("Not yet implemented")
    }

}
