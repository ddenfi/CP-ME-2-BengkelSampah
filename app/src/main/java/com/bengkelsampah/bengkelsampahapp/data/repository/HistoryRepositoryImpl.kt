package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.domain.repository.HistoryRepository
import com.bengkelsampah.bengkelsampahapp.domain.model.DummyData
import com.bengkelsampah.bengkelsampahapp.domain.model.DummyHistoryData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor() : HistoryRepository {
    override fun getActiveTransaction(): Flow<List<DummyHistoryData>> = flow {
        delay(5000)
        emit(DummyData.generateDummyData())
    }
}