package playwithyou.lucca.infrastructure.repositories

import MongoClientSingleton
import com.mongodb.MongoException
import com.mongodb.client.MongoCollection
import org.bson.Document
import playwithyou.lucca.domain.entity.Seller
import playwithyou.lucca.domain.interfaces.ISellerRepository

class SellerRepository : ISellerRepository {

    override fun store(seller: Seller) = try {
        val collection = getCollection()
        val document = createDocument(seller)
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

    override fun getFromMail(mail: String): Seller? {
        val collection = getCollection()
        val document = collection.find(Document("mail", mail)).first()
        return if (document != null) {
            createSeller(document)
        } else {
            null
        }
    }

    private fun createSeller(document: Document) =
        Seller(
            document["id"] as String, document["name"] as String, document["phone"] as String,
            document["mail"] as String, document["activate"] as Boolean)

    private fun createDocument(seller: Seller): Document {
        val document = Document()
        document["id"] = seller.id
        document["name"] = seller.name
        document["phone"] = seller.phone
        document["mail"] = seller.mail
        document["activate"] = seller.activate
        return document
    }

    private fun getCollection(): MongoCollection<Document> {
        var database = getDataBase()!!
        return database.getCollection("Seller")
    }

    private fun getDataBase() = MongoClientSingleton.mongoClient?.getDatabase("TinderHouse")

}
