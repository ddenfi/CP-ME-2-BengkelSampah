package com.bengkelsampah.bengkelsampahapp.domain.repository

import com.bengkelsampah.bengkelsampahapp.ui.history.DummyHistoryData
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun getActiveTransaction(): Flow<List<DummyHistoryData>>

}