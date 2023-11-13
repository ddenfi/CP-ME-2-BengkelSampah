package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.WasteSoldModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.WasteBoxRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WasteBoxRepositoryImpl @Inject constructor() : WasteBoxRepository {
    override fun getWasteBoxItems(): Flow<List<WasteSoldModel>> = flow {
        delay(2000)
        emit(WasteSoldModel.cartDummyData)
    }
}