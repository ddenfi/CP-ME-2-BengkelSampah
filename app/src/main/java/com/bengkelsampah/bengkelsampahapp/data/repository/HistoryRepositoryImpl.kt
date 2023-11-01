package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.domain.repository.HistoryRepository
import com.bengkelsampah.bengkelsampahapp.ui.history.DummyData
import com.bengkelsampah.bengkelsampahapp.ui.history.DummyHistoryData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HistoryRepositoryImpl : HistoryRepository {
    override fun getActiveTransaction(): Flow<List<DummyHistoryData>> = flow {
        delay(5000)
        emit(DummyData.generateDummyData())
    }
}