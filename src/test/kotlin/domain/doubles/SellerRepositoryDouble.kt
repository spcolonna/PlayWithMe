package domain.doubles

import playwithyou.lucca.domain.entity.Seller
import playwithyou.lucca.domain.interfaces.ISellerRepository

class SellerRepositoryDouble : ISellerRepository {

    lateinit var lastVendorIdCalled: String
    lateinit var lastVendorStored: Seller
    var wasCalled = false
    override fun store(seller: Seller) {
        wasCalled = true
        lastVendorStored = seller
    }

    override fun delete(sellerId: String) {
        wasCalled = true
        lastVendorIdCalled = sellerId
    }
}