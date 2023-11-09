package com.bengkelsampah.bengkelsampahapp.domain.model

data class DummyHistoryData(
    val id: Int,
    val date: String,
    val address: String,
    val pickUpDate: String,
    val status: String,
    val agent: String,
    val agentAddress: String,
    val agentPhone: String,
    val waste: List<DummyWaste>,
    val total: Int,
    val description: String?
)

data class DummyWaste(
    val name: String,
    val amount: Double,
    val unit: String,
    val pricePerUnit: Int
)

object DummyData {
    fun generateDummyData(): List<DummyHistoryData> {
        val historyData = ArrayList<DummyHistoryData>()

        historyData.add(
            DummyHistoryData(
                id = 0,
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = "Menunggu Konfirmasi",
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = listOf(
                    DummyWaste(
                        name = "botol kecap",
                        amount = 25.0,
                        unit = "pcs",
                        pricePerUnit = 200
                    ),
                    DummyWaste(
                        name = "alma lembek (panci)",
                        amount = 2.4,
                        unit = "kg",
                        pricePerUnit = 10000
                    ),
                    DummyWaste(
                        name = "besi sam-sam",
                        amount = 25.0,
                        unit = "kg",
                        pricePerUnit = 200
                    ),
                    DummyWaste(
                        name = "kara hitam",
                        amount = 5.8,
                        unit = "kg",
                        pricePerUnit = 1000
                    ),
                    DummyWaste(
                        name = "plastik pp (kemasan snack)",
                        amount = 1.2,
                        unit = "kg",
                        pricePerUnit = 300
                    ),
                    DummyWaste(
                        name = "himpek/printer",
                        amount = 2.3,
                        unit = "kg",
                        pricePerUnit = 500
                    ),
                    DummyWaste(
                        name = "karton",
                        amount = 54.5,
                        unit = "kg",
                        pricePerUnit = 1000
                    )
                ),
                total = 71460,
                description = "Mohon telfon dulu sebelum sampai rumah"
            )
        )
        historyData.add(
            DummyHistoryData(
                id = 1,
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = "Dibatalkan",
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = listOf(
                    DummyWaste(
                        name = "botol kecap",
                        amount = 25.0,
                        unit = "pcs",
                        pricePerUnit = 200
                    ),
                    DummyWaste(
                        name = "alma lembek (panci)",
                        amount = 2.4,
                        unit = "kg",
                        pricePerUnit = 10000
                    ),
                    DummyWaste(
                        name = "besi sam-sam",
                        amount = 25.0,
                        unit = "kg",
                        pricePerUnit = 200
                    ),
                    DummyWaste(
                        name = "kara hitam",
                        amount = 5.8,
                        unit = "kg",
                        pricePerUnit = 1000
                    ),
                    DummyWaste(
                        name = "plastik pp (kemasan snack)",
                        amount = 1.2,
                        unit = "kg",
                        pricePerUnit = 300
                    ),
                    DummyWaste(
                        name = "himpek/printer",
                        amount = 2.3,
                        unit = "kg",
                        pricePerUnit = 500
                    ),
                    DummyWaste(
                        name = "karton",
                        amount = 54.5,
                        unit = "kg",
                        pricePerUnit = 1000
                    )
                ),
                total = 71460,
                description = "Mohon telfon dulu sebelum sampai rumah"
            )
        )
        historyData.add(
            DummyHistoryData(
                id = 2,
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = "Selesai",
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = listOf(
                    DummyWaste(
                        name = "botol kecap",
                        amount = 25.0,
                        unit = "pcs",
                        pricePerUnit = 200
                    ),
                    DummyWaste(
                        name = "alma lembek (panci)",
                        amount = 2.4,
                        unit = "kg",
                        pricePerUnit = 10000
                    ),
                    DummyWaste(
                        name = "besi sam-sam",
                        amount = 25.0,
                        unit = "kg",
                        pricePerUnit = 200
                    ),
                    DummyWaste(
                        name = "kara hitam",
                        amount = 5.8,
                        unit = "kg",
                        pricePerUnit = 1000
                    ),
                    DummyWaste(
                        name = "plastik pp (kemasan snack)",
                        amount = 1.2,
                        unit = "kg",
                        pricePerUnit = 300
                    ),
                    DummyWaste(
                        name = "himpek/printer",
                        amount = 2.3,
                        unit = "kg",
                        pricePerUnit = 500
                    ),
                    DummyWaste(
                        name = "karton",
                        amount = 54.5,
                        unit = "kg",
                        pricePerUnit = 1000
                    )
                ),
                total = 71460,
                description = "Mohon telfon dulu sebelum sampai rumah"
            )
        )
        historyData.add(
            DummyHistoryData(
                id = 3,
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = "Diproses",
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = listOf(
                    DummyWaste(
                        name = "botol kecap",
                        amount = 25.0,
                        unit = "pcs",
                        pricePerUnit = 200
                    ),
                    DummyWaste(
                        name = "alma lembek (panci)",
                        amount = 2.4,
                        unit = "kg",
                        pricePerUnit = 10000
                    ),
                    DummyWaste(
                        name = "besi sam-sam",
                        amount = 25.0,
                        unit = "kg",
                        pricePerUnit = 200
                    ),
                    DummyWaste(
                        name = "kara hitam",
                        amount = 5.8,
                        unit = "kg",
                        pricePerUnit = 1000
                    ),
                    DummyWaste(
                        name = "plastik pp (kemasan snack)",
                        amount = 1.2,
                        unit = "kg",
                        pricePerUnit = 300
                    ),
                    DummyWaste(
                        name = "himpek/printer",
                        amount = 2.3,
                        unit = "kg",
                        pricePerUnit = 500
                    ),
                    DummyWaste(
                        name = "karton",
                        amount = 54.5,
                        unit = "kg",
                        pricePerUnit = 1000
                    )
                ),
                total = 71460,
                description = "Mohon telfon dulu sebelum sampai rumah"
            )
        )
        return historyData
    }
}