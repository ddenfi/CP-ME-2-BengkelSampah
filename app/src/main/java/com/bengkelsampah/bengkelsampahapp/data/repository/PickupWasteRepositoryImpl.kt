package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.PickupWasteRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PickupWasteRepositoryImpl @Inject constructor(): PickupWasteRepository {
    override fun getAllOrders(): Flow<List<WasteOrderModel>> = flow {
        delay(2000)
        emit(WasteOrderModel.dummyData)
    }

    override fun getOrderById(ids: String): Flow<WasteOrderModel> = flow {
        val dummyOrder = WasteOrderModel.dummyData.find {
            it.id == ids
        }
        delay(2000)

        if (dummyOrder == null) {
            throw Error()
        } else {
            emit(dummyOrder)
        }
    }
}