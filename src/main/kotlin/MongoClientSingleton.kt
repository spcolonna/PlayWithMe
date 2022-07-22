import com.mongodb.MongoClient
import com.mongodb.MongoException
import org.bson.Document

class MongoClientSingleton {
    fun init() = try {
        mongoClient = MongoClient("127.0.0.1", 27017)
        println("Kotlin connected to MongoDB!")
    } catch (e: MongoException) {
        e.printStackTrace()
    } finally {
        //mongoClient!!.close()
    }

    companion object {
        var mongoClient: MongoClient? = null
    }

}
