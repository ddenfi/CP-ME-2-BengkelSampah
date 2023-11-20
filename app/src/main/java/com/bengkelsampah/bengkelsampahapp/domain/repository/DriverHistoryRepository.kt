package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.HistoryModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import kotlinx.coroutines.flow.Flow

interface DriverHistoryRepository {
    fun getOrdersHistory(): Flow<List<WasteOrderModel>>

    fun getOrderHistoryById(id: String): Flow<WasteOrderModel>
}