package com.bengkelsampah.bengkelsampahapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bengkelsampah.bengkelsampahapp.domain.model.OrderStatus
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel

@Entity(tableName = "OfflineWasteOrder")
data class WasteOrderEntity(
    @PrimaryKey
    val orderId: String,
    val consumerName: String? = "",
    val date: String,
    val address: String,
    val pickUpDate: String,
    val status: String,
    val agent: String,
    val agentAddress: String,
    val agentPhone: String,
    val wasteBox: List<WasteBoxEntity>? = listOf(),
    val total: Int,
    val description: String? = "",
    val driverName: String? = "",
    val distance: Double
)

fun WasteOrderEntity.asExternalModel(): WasteOrderModel {
    return WasteOrderModel(
        id = orderId,
        consumerName = consumerName,
        date = date,
        address = address,
        pickUpDate = pickUpDate,
        status = OrderStatus.valueOf(status),
        agent = agent,
        agentAddress = agentAddress,
        agentPhone = agentPhone,
        wasteBox = wasteBox?.map { it.asExternalLayer() } ?: listOf(),
        total = total,
        description = description,
        driverName = driverName,
        distance = distance
    )
}