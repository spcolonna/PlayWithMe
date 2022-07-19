package domain.doubles

import playwithyou.lucca.domain.Given
import playwithyou.lucca.domain.entity.Seller
import playwithyou.lucca.domain.interfaces.ISellerRepository

class SellerRepositoryDouble : ISellerRepository {

    private lateinit var seller: Seller
    lateinit var lastVendorIdCalled: String
    lateinit var lastVendorStored: Seller
    var wasCalled = false

    constructor(){
        seller = Given.aSeller()
    }
    constructor(seller: Seller) {
        this.seller = seller
    }

    override fun store(seller: Seller) {
        wasCalled = true
        lastVendorStored = seller
    }

    override fun delete(sellerId: String) {
        wasCalled = true
        lastVendorIdCalled = sellerId
    }

    override fun get(id: String): Seller? {
        if(seller.id == id)
            return seller
        return null
    }
}