package com.bengkelsampah.bengkelsampahapp.data.source.local.room.util

import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteOrderEntity
import com.bengkelsampah.bengkelsampahapp.domain.model.OrderStatus
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel

class DataMapper {
    fun toExternalLayer(data:WasteOrderEntity):WasteOrderModel{
        return WasteOrderModel(
            id = "platea",
            consumerName = null,
            date = "donec",
            address = "augue",
            pickUpDate = "an",
            status = OrderStatus.CANCELLED,
            agent = "laoreet",
            agentAddress = "venenatis",
            agentPhone = "(757) 955-7015",
            wasteBox = listOf(),
            total = 6655,
            description = null,
            driverName = null,
            distance = 2.3
        )
    }
}