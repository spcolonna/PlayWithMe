package domain.doubles

import playwithyou.lucca.domain.Given
import playwithyou.lucca.domain.entity.Seller
import playwithyou.lucca.domain.interfaces.ISellerRepository

class SellerRepositoryDouble : ISellerRepository {

    private var seller: Seller
    lateinit var lastVendorIdCalled: String
    var lastVendorStored = Given.aSeller()
    var wasCalled = false

    constructor(){
        seller = Given.aSeller("unknownId", "unknownName", "unknownEmail", "unknownPassword")
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

    override fun getFromMail(mail: String): Seller? {
        if(seller.mail == mail)
            return seller
        return null
    }
}