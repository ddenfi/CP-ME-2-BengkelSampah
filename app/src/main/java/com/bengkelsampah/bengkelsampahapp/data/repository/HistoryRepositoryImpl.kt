package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.domain.repository.HistoryRepository
import com.bengkelsampah.bengkelsampahapp.domain.model.HistoryModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor() : HistoryRepository {
    override fun getActiveTransaction(): Flow<List<HistoryModel>> = flow {
        delay(2000)
        emit(HistoryModel.dummyData)
    }

    override fun getHistoryById(id: Int): Flow<HistoryModel> = flow {
        delay(2000)
        emit(HistoryModel.dummyData[id])
    }
}