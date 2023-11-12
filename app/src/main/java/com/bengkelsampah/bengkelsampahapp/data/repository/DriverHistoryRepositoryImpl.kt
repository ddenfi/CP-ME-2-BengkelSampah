package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.DummyData
import com.bengkelsampah.bengkelsampahapp.domain.model.DummyHistoryData
import com.bengkelsampah.bengkelsampahapp.domain.repository.DriverHistoryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DriverHistoryRepositoryImpl @Inject constructor():DriverHistoryRepository {
    override fun getActiveTransaction(): Flow<List<DummyHistoryData>> {
        return flow {
            delay(5000)
            emit(DummyData.generateDummyData())
        }
    }

    override fun getHistoryById(id: Int): Flow<DummyHistoryData> {
        return flow {
            delay(2000)
            emit(DummyData.generateDummyData()[id])
        }
    }
}