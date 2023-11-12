package com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner
import java.util.UUID

object PartnerDummyData {

    fun getDummyPartnerList(): List<GetPartnerItem> {
        val partnerList = ArrayList<GetPartnerItem>()

        partnerList.add(GetPartnerItem(UUID.randomUUID(), "Jl. Sana sini", "0827242424", "BS. Pulau Gadung"))
        partnerList.add(GetPartnerItem(UUID.randomUUID(), "Jl. Sana sini", "0272794922", "BS. Banguntapan"))
        partnerList.add(GetPartnerItem(UUID.randomUUID(), "Jl. Sana sini", "0824284282", "BS. Bola Bali"))
        partnerList.add(GetPartnerItem(UUID.randomUUID(), "Jl. Sana sini", "0827242424", "BS. Pulau Gadung"))
        partnerList.add(GetPartnerItem(UUID.randomUUID(), "Jl. Sana sini", "0272794922", "BS. Banguntapan"))
        partnerList.add(GetPartnerItem(UUID.randomUUID(), "Jl. Sana sini", "0824284282", "BS. Bola Bali"))
        partnerList.add(GetPartnerItem(UUID.randomUUID(), "Jl. Sana sini", "0827242424", "BS. Pulau Gadung"))
        partnerList.add(GetPartnerItem(UUID.randomUUID(), "Jl. Sana sini", "0272794922", "BS. Banguntapan"))
        partnerList.add(GetPartnerItem(UUID.randomUUID(), "Jl. Sana sini", "0824284282", "BS. Bola Bali"))

        return partnerList
    }
}
