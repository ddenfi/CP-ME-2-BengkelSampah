package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.DummyHistoryData
import kotlinx.coroutines.flow.Flow

interface DriverHistoryRepository {
    fun getActiveTransaction(): Flow<List<DummyHistoryData>>

    fun getHistoryById(id: Int): Flow<DummyHistoryData>
}