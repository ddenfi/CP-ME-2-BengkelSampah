package com.bengkelsampah.bengkelsampahapp.domain.model

import com.bengkelsampah.bengkelsampahapp.ui.history.HistoryStatus

data class WasteOrderModel(
    val id: Int,
    val consumerName:String?,
    val date: String,
    val address: String,
    val pickUpDate: String,
    val status: OrderStatus,
    val agent: String,
    val agentAddress: String,
    val agentPhone: String,
    val waste: List<WasteModel>,
    val total: Int,
    val description: String?,
    val driverName:String?,
    val distance:Double
){
    companion object {
        val dummyData = listOf(
            WasteOrderModel(
                id = 1,
                consumerName = "Lesti Kejora",
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = OrderStatus.PROCESSED,
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = WasteModel.dummyData,
                total = 6,
                driverName = null,
                distance = 3.0,
                description = null
            ),
            WasteOrderModel(
                id = 2,
                date = "2 Oktober 2023, 12:00",
                consumerName = "Lesti Kejora",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = OrderStatus.DONE,
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = WasteModel.dummyData,
                total = 6,
                driverName = "Wahyu",
                distance = 3.0,
                description = null
            ),
            WasteOrderModel(
                id = 3,
                consumerName = "Lesti Kejora",
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = OrderStatus.PICKING_UP,
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = WasteModel.dummyData,
                total = 6,
                distance = 3.0,
                driverName = "Wahyu",
                description = null
            ),
            WasteOrderModel(
                id = 4,
                consumerName = "Lesti Kejora",
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = OrderStatus.WAIT_CONFIRMATION,
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                waste = WasteModel.dummyData,
                total = 6,
                distance = 3.0,
                driverName = null,
                description = null
            )
        )
    }
}
