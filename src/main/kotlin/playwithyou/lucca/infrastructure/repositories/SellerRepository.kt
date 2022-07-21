package playwithyou.lucca.infrastructure.repositories

import playwithyou.lucca.domain.entity.Seller
import playwithyou.lucca.domain.interfaces.ISellerRepository

class SellerRepository : ISellerRepository{
    override fun store(seller: Seller) {
        TODO("Not yet implemented")
    }

    override fun delete(sellerId: String) {
        TODO("Not yet implemented")
    }

    override fun get(id: String): Seller? {
        TODO("Not yet implemented")
    }

}
