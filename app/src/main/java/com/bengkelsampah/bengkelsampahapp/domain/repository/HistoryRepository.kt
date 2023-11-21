package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun getAllOrders(): Flow<List<WasteOrderModel>>

    fun getHistoryById(id: String): Flow<WasteOrderModel>
}