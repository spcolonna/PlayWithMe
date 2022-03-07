package domain.doubles

import playwithyou.lucca.domain.entity.Vendor
import playwithyou.lucca.domain.interfaces.IVendorRepository

class VendorRepositoryDouble : IVendorRepository {

    lateinit var lastVendorStored: Vendor
    var wasCalled = false
    override fun store(vendor: Vendor) {
        wasCalled = true
        lastVendorStored = vendor
    }
}