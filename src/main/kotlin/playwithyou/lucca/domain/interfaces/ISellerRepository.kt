package playwithyou.lucca.domain.interfaces

import playwithyou.lucca.domain.entity.Seller

interface ISellerRepository {
    fun store(seller: Seller)
    fun delete(sellerId: String)
    fun get(id: String): Seller?
    fun getFromMail(mail: String): Seller?

}