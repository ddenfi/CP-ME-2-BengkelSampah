package com.bengkelsampah.bengkelsampahapp.domain.model

import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteBoxEntity
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteOrderEntity

data class WasteOrderModel(
    val id: String,
    val consumerName: String?,
    val date: String,
    val address: String,
    val pickUpDate: String,
    val status: OrderStatus,
    val agent: String,
    val agentAddress: String,
    val agentPhone: String,
    val wasteBox: List<WasteBoxModel>,
    val total: Int,
    val description: String?,
    val driverName: String?,
    val distance: Double
) {
    companion object {
        val dummyData = listOf(
            WasteOrderModel(
                id = "S-111",
                consumerName = "Lesti Kejora",
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = OrderStatus.PROCESSED,
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                wasteBox = WasteBoxModel.dummyData,
                total = 6,
                driverName = null,
                distance = 3.0,
                description = null
            ),
            WasteOrderModel(
                id = "S-222",
                date = "2 Oktober 2023, 12:00",
                consumerName = "Lesti Kejora",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = OrderStatus.DONE,
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                wasteBox = WasteBoxModel.dummyData,
                total = 6,
                driverName = "Wahyu",
                distance = 3.0,
                description = null
            ),
            WasteOrderModel(
                id = "S-333",
                consumerName = "Lesti Kejora",
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = OrderStatus.PICKING_UP,
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                wasteBox = WasteBoxModel.dummyData,
                total = 6,
                distance = 3.0,
                driverName = "Wahyu",
                description = null
            ),
            WasteOrderModel(
                id = "S-444",
                consumerName = "Lesti Kejora",
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = OrderStatus.WAIT_CONFIRMATION,
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                wasteBox = WasteBoxModel.dummyData,
                total = 6,
                distance = 3.0,
                driverName = null,
                description = null
            ),
            WasteOrderModel(
                id = "S-555",
                consumerName = "Lesti Kejora",
                date = "2 Oktober 2023, 12:00",
                address = "Jl. Kesana Kemari No. 123, Kec. A, Kab. B",
                pickUpDate = "5 Oktober 2023, 10:00",
                status = OrderStatus.CANCELLED,
                agent = "BS AGEN PASAR MINGGU",
                agentAddress = "Jl. TB Simatupang No.Kav38, RW.8, Jati Padang, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12540",
                agentPhone = "089675234567",
                wasteBox = WasteBoxModel.dummyData,
                total = 6,
                distance = 3.0,
                driverName = null,
                description = null
            )
        )
    }
}

fun WasteOrderModel.asWasteOrderEntity() = WasteOrderEntity(
    orderId = id,
    consumerName = consumerName,
    date = date,
    address = address,
    pickUpDate = pickUpDate,
    status = status.name,
    agent = agent,
    agentAddress = agentAddress,
    agentPhone = agentPhone,
    wasteBox = wasteBox.map { it.asWasteBoxEntity() },
    total = total,
    description = description,
    driverName = driverName,
    distance = distance
)
