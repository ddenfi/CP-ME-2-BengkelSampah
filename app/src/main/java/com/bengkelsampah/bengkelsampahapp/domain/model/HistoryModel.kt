package com.bengkelsampah.bengkelsampahapp.domain.model

import com.bengkelsampah.bengkelsampahapp.ui.history.HistoryStatus

data class HistoryModel(
    val id: Int,
    val date: String,
    val address: String,
    val pickUpDate: String,
    val status: String,
    val agent: String,
    val agentAddress: String,
    val agentPhone: String,
    val waste: List<WasteSoldModel>,
    val total: Int,
    val description: String?
) {
    companion object {
        val dummyData = listOf(
            HistoryModel(
                id = 0,
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = HistoryStatus.MENUNGGU_KONFIRMASI.statusValue,
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = listOf(
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "botol kecap",
                            unit = WasteUnit.UNIT.abbreviation,
                            pricePerUnit = 200
                        ),
                        amount = 25
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "alma lembek (panci)",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 10000
                        ),
                        amount = 2
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "besi sam-sam",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 200
                        ),
                        amount = 25
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "kara hitam",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 1000
                        ),
                        amount = 5
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "plastik pp (kemasan snack)",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 300
                        ),
                        amount = 1
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "himpek/printer",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 500
                        ),
                        amount = 2
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "karton",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 1000
                        ),
                        amount = 54
                    )
                ),
                total = 71460,
                description = "Mohon telfon dulu sebelum sampai rumah"
            ),
            HistoryModel(
                id = 1,
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = HistoryStatus.DIBATALKAN.statusValue,
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = listOf(
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "botol kecap",
                            unit = WasteUnit.UNIT.abbreviation,
                            pricePerUnit = 200
                        ),
                        amount = 25
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "alma lembek (panci)",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 10000
                        ),
                        amount = 2
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "besi sam-sam",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 200
                        ),
                        amount = 25
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "kara hitam",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 1000
                        ),
                        amount = 5
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "plastik pp (kemasan snack)",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 300
                        ),
                        amount = 1
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "himpek/printer",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 500
                        ),
                        amount = 2
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "karton",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 1000
                        ),
                        amount = 54
                    )
                ),
                total = 71460,
                description = "Mohon telfon dulu sebelum sampai rumah"
            ),
            HistoryModel(
                id = 2,
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = HistoryStatus.SELESAI.statusValue,
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = listOf(
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "botol kecap",
                            unit = WasteUnit.UNIT.abbreviation,
                            pricePerUnit = 200
                        ),
                        amount = 25
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "alma lembek (panci)",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 10000
                        ),
                        amount = 2
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "besi sam-sam",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 200
                        ),
                        amount = 25
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "kara hitam",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 1000
                        ),
                        amount = 5
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "plastik pp (kemasan snack)",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 300
                        ),
                        amount = 1
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "himpek/printer",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 500
                        ),
                        amount = 2
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "karton",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 1000
                        ),
                        amount = 54
                    )
                ),
                total = 71460,
                description = "Mohon telfon dulu sebelum sampai rumah"
            ),
            HistoryModel(
                id = 3,
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = HistoryStatus.DIPROSES.statusValue,
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = listOf(
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "botol kecap",
                            unit = WasteUnit.UNIT.abbreviation,
                            pricePerUnit = 200
                        ),
                        amount = 25
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "alma lembek (panci)",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 10000
                        ),
                        amount = 2
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "besi sam-sam",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 200
                        ),
                        amount = 25
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "kara hitam",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 1000
                        ),
                        amount = 6
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "plastik pp (kemasan snack)",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 300
                        ),
                        amount = 1
                    ),
                    WasteSoldModel(
                        waste = WasteModel(
                            name = "himpek/printer",
                            unit = WasteUnit.KG.abbreviation,
                            pricePerUnit = 500
                        ),
                        amount = 2
                    ),
                ),
                total = 71460,
                description = "Mohon telfon dulu sebelum sampai rumah"
            )
        )
    }
}
