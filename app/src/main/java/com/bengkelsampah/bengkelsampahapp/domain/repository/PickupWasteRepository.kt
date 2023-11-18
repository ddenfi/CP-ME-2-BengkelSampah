package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import kotlinx.coroutines.flow.Flow

interface PickupWasteRepository {

    fun getAllOrders(): Flow<List<WasteOrderModel>>

    fun getOrderById(ids: String):Flow<WasteOrderModel>
}