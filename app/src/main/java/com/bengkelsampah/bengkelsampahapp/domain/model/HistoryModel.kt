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
    val waste: List<WasteBoxModel>,
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
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "botol kecap",
                            unit = WasteUnit.UNIT,
                            pricePerUnit = 200
                        ),
                        amount = 25.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "alma lembek (panci)",
                            unit = WasteUnit.KG,
                            pricePerUnit = 10000
                        ),
                        amount = 2.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "besi sam-sam",
                            unit = WasteUnit.KG,
                            pricePerUnit = 200
                        ),
                        amount = 25.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "kara hitam",
                            unit = WasteUnit.KG,
                            pricePerUnit = 1000
                        ),
                        amount = 5.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "plastik pp (kemasan snack)",
                            unit = WasteUnit.KG,
                            pricePerUnit = 300
                        ),
                        amount = 1.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "himpek/printer",
                            unit = WasteUnit.KG,
                            pricePerUnit = 500
                        ),
                        amount = 2.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            name = "karton",
                            wasteType = "INORGAIC",
                            unit = WasteUnit.KG,
                            pricePerUnit = 1000
                        ),
                        amount = 54.0
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
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "botol kecap",
                            unit = WasteUnit.UNIT,
                            pricePerUnit = 200
                        ),
                        amount = 25.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "alma lembek (panci)",
                            unit = WasteUnit.KG,
                            pricePerUnit = 10000
                        ),
                        amount = 2.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "besi sam-sam",
                            unit = WasteUnit.KG,
                            pricePerUnit = 200
                        ),
                        amount = 25.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "kara hitam",
                            unit = WasteUnit.KG,
                            pricePerUnit = 1000
                        ),
                        amount = 5.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "plastik pp (kemasan snack)",
                            unit = WasteUnit.KG,
                            pricePerUnit = 300
                        ),
                        amount = 1.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "himpek/printer",
                            unit = WasteUnit.KG,
                            pricePerUnit = 500
                        ),
                        amount = 2.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            name = "karton",
                            wasteType = "INORGAIC",
                            unit = WasteUnit.KG,
                            pricePerUnit = 1000
                        ),
                        amount = 54.0
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
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "botol kecap",
                            unit = WasteUnit.UNIT,
                            pricePerUnit = 200
                        ),
                        amount = 25.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "alma lembek (panci)",
                            unit = WasteUnit.KG,
                            pricePerUnit = 10000
                        ),
                        amount = 2.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "besi sam-sam",
                            unit = WasteUnit.KG,
                            pricePerUnit = 200
                        ),
                        amount = 25.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "kara hitam",
                            unit = WasteUnit.KG,
                            pricePerUnit = 1000
                        ),
                        amount = 5.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "plastik pp (kemasan snack)",
                            unit = WasteUnit.KG,
                            pricePerUnit = 300
                        ),
                        amount = 1.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "himpek/printer",
                            unit = WasteUnit.KG,
                            pricePerUnit = 500
                        ),
                        amount = 2.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            name = "karton",
                            wasteType = "INORGAIC",
                            unit = WasteUnit.KG,
                            pricePerUnit = 1000
                        ),
                        amount = 54.0
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
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "botol kecap",
                            unit = WasteUnit.UNIT,
                            pricePerUnit = 200
                        ),
                        amount = 25.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "alma lembek (panci)",
                            unit = WasteUnit.KG,
                            pricePerUnit = 10000
                        ),
                        amount = 2.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "besi sam-sam",
                            unit = WasteUnit.KG,
                            pricePerUnit = 200
                        ),
                        amount = 25.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "kara hitam",
                            unit = WasteUnit.KG,
                            pricePerUnit = 1000
                        ),
                        amount = 5.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "plastik pp (kemasan snack)",
                            unit = WasteUnit.KG,
                            pricePerUnit = 300
                        ),
                        amount = 1.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            wasteType = "INORGAIC",
                            name = "himpek/printer",
                            unit = WasteUnit.KG,
                            pricePerUnit = 500
                        ),
                        amount = 2.0
                    ),
                    WasteBoxModel(
                        waste = WasteModel(
                            wasteId = "WS-1",
                            name = "karton",
                            wasteType = "INORGAIC",
                            unit = WasteUnit.KG,
                            pricePerUnit = 1000
                        ),
                        amount = 54.0
                    )
                ),
                total = 71460,
                description = "Mohon telfon dulu sebelum sampai rumah"
            )
        )
    }
}
