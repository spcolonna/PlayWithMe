package playwithyou.lucca.domain.interfaces

import playwithyou.lucca.domain.entity.Vendor

interface IVendorRepository {
    fun store(vendor: Vendor)
    fun delete(vendorId: String)

}