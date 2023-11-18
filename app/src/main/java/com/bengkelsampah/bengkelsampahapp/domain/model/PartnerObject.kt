package com.bengkelsampah.bengkelsampahapp.domain.model

import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.GetPartner
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.GetPartnerItem
import com.bengkelsampah.bengkelsampahapp.data.source.remote.response.partner.PartnerById
import java.util.UUID

object PartnerObject {
    val partnerDummyData = GetPartner(
        message = "Success retrieved partners",
        data = listOf(
            GetPartnerItem(
                UUID.randomUUID(),
                "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                "0827242424",
                "BS. Pulau Gadung"
            ),
            GetPartnerItem(
                UUID.randomUUID(),
                "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                "0272794922",
                "BS. Banguntapan"
            ),
            GetPartnerItem(
                UUID.randomUUID(),
                "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                "0824284282",
                "BS. Bola Bali"
            ),
            GetPartnerItem(
                UUID.randomUUID(),
                "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                "0827242424",
                "BS. Pulau Gadung"
            ),
            GetPartnerItem(
                UUID.randomUUID(),
                "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                "0824284282",
                "BS. Bola Bali"
            ),
            GetPartnerItem(
                UUID.randomUUID(),
                "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                "0827242424",
                "BS. Pulau Gadung"
            ),
            GetPartnerItem(
                UUID.randomUUID(),
                "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                "0272794922",
                "BS. Banguntapan"
            ),
            GetPartnerItem(
                UUID.randomUUID(),
                "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                "0824284282",
                "BS. Bola Bali"
            )
        )
    )

    val partnerByIdDummyData = PartnerById(
        message = "Success retrieving partner",
        data = GetPartnerItem(
            UUID.randomUUID(), "Jl. Sana sini", "0827242424", "BS. Pulau Gadung"
        )
    )
}
