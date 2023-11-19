package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.HistoryModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.DriverHistoryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DriverHistoryRepositoryImpl @Inject constructor():DriverHistoryRepository {
    override fun getActiveTransaction(): Flow<List<HistoryModel>> {
        return flow {
            delay(5000)
            emit(HistoryModel.dummyData)
        }
    }

    override fun getHistoryById(id: Int): Flow<HistoryModel> {
        return flow {
            delay(2000)
            emit(HistoryModel.dummyData[id])
        }
    }
}