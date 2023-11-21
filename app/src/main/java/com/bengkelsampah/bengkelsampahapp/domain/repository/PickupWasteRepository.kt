package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.OrderStatus
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import kotlinx.coroutines.flow.Flow

interface PickupWasteRepository {
    fun getAllOrders(): Flow<List<WasteOrderModel>>
    fun getOrderById(ids: String):Flow<WasteOrderModel>
    fun getAllWaste():Flow<List<WasteModel>>
    fun searchWaste(query:String):Flow<List<WasteModel>>
    suspend fun updateOrder(wasteOrder:WasteOrderModel)

}