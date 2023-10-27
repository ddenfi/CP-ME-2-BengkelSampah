package com.bengkelsampah.bengkelsampahapp.ui.history

data class DummyHistoryData(
    val date: String,
    val address: String,
    val pickUpDate: String,
    val status: String,
    val agent: String,
    val agentAddress: String,
    val agentPhone: String,
    val waste: Map<String, Number>,
    val total: Int,
    val description: String?
)

data class DummyWaste(
    val name: String,
    val unit: String,
    val pricePerUnit: Int
)

object DummyData {
    fun generateDummyData(): List<DummyHistoryData> {
        val historyData = ArrayList<DummyHistoryData>()

        historyData.add(
            DummyHistoryData(
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = "Menunggu Konfirmasi",
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = mapOf(
                    "botol kecap" to 25,
                    "alma lembek (panci)" to 2.4,
                    "besi sam-sam" to 25,
                    "kara hitam" to 5.8,
                    "plastik pp (kemasan snack)" to 1.2,
                    "himpek/printer" to 2.3,
                    "karton" to 54.5
                ),
                total = 71460,
                description = "Mohon telfon dulu sebelum sampai rumah"
            )
        )
        historyData.add(
            DummyHistoryData(
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = "Dibatalkan",
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = mapOf(
                    "botol kecap" to 25,
                    "alma lembek (panci)" to 2.4,
                    "besi sam-sam" to 25,
                    "kara hitam" to 5.8,
                    "plastik pp (kemasan snack)" to 1.2,
                    "himpek/printer" to 2.3,
                    "karton" to 54.5
                ),
                total = 71460,
                description = "Mohon telfon dulu sebelum sampai rumah"
            )
        )
        historyData.add(
            DummyHistoryData(
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = "Selesai",
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = mapOf(
                    "botol kecap" to 25,
                    "alma lembek (panci)" to 2.4,
                    "besi sam-sam" to 25,
                    "kara hitam" to 5.8,
                    "plastik pp (kemasan snack)" to 1.2,
                    "himpek/printer" to 2.3,
                    "karton" to 54.5
                ),
                total = 71460,
                description = "Mohon telfon dulu sebelum sampai rumah"
            )
        )
        historyData.add(
            DummyHistoryData(
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = "Diproses",
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = mapOf(
                    "botol kecap" to 25,
                    "alma lembek (panci)" to 2.4,
                    "besi sam-sam" to 25,
                    "kara hitam" to 5.8,
                    "plastik pp (kemasan snack)" to 1.2,
                    "himpek/printer" to 2.3,
                    "karton" to 54.5
                ),
                total = 71460,
                description = "Mohon telfon dulu sebelum sampai rumah"
            )
        )
        return historyData
    }

    fun generateDummyWaste(): List<DummyWaste> {
        val waste = ArrayList<DummyWaste>()

        waste.add(
            DummyWaste(
                name = "botol kecap",
                unit = "pcs",
                pricePerUnit = 200
            )
        )
        waste.add(
            DummyWaste(
                name = "alma lembek (panci)",
                unit = "kg",
                pricePerUnit = 10000
            )
        )
        waste.add(
            DummyWaste(
                name = "besi sam-sam",
                unit = "kg",
                pricePerUnit = 200
            )
        )
        waste.add(
            DummyWaste(
                name = "kara hitam",
                unit = "kg",
                pricePerUnit = 1000
            )
        )
        waste.add(
            DummyWaste(
                name = "plastik pp (kemasan snack)",
                unit = "kg",
                pricePerUnit = 300
            )
        )
        waste.add(
            DummyWaste(
                name = "himpek/printer",
                unit = "kg",
                pricePerUnit = 500
            )
        )
        waste.add(
            DummyWaste(
                name = "karton",
                unit = "kg",
                pricePerUnit = 1000
            )
        )

        return waste
    }
}