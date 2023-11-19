package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.WasteModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.WasteBoxRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WasteBoxRepositoryImpl @Inject constructor() : WasteBoxRepository {
    override fun getWasteBoxItems(): Flow<List<WasteBoxModel>> = flow {
        delay(2000)
        emit(WasteBoxModel.dummyData)
    }

    override fun getWasteTypes(): Flow<List<WasteModel>> = flow {
        delay(2000)
        emit(WasteModel.wasteDummyData)
    }
}