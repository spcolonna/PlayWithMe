package playwithyou.lucca.infrastructure.repositories

import MongoClientSingleton
import com.mongodb.MongoException
import com.mongodb.client.MongoCollection
import org.bson.Document
import playwithyou.lucca.domain.entity.Seller
import playwithyou.lucca.domain.interfaces.ISellerRepository

class SellerRepository : ISellerRepository {

    override fun store(seller: Seller) = try {
        var collection = getCollection()
        var document = createDocument(seller)
        collection.insertOne(document)
    } catch (e: MongoException) {
        e.printStackTrace()
    }

    override fun delete(sellerId: String) {
        TODO("Not yet implemented")
    }

    override fun get(id: String): Seller? {
        TODO("Not yet implemented")
    }

    private fun createDocument(seller: Seller): Document {
        var document = Document()
        document["id"] = seller.id
        document["name"] = seller.name
        document["phone"] = seller.phone
        document["mail"] = seller.mail
        return document
    }

    private fun getCollection(): MongoCollection<Document> {
        var database = getDataBase()!!
        return database.getCollection("Seller")
    }

    private fun getDataBase() = MongoClientSingleton.mongoClient?.getDatabase("TinderHouse")

}
