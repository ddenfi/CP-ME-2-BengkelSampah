package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteOrderEntity
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.asExternalModel
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.WasteOrderDao
import com.bengkelsampah.bengkelsampahapp.domain.repository.HistoryRepository
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val orderDao: WasteOrderDao
) : HistoryRepository {
    override fun getAllOrders(): Flow<List<WasteOrderModel>> =
        orderDao.getAllOrder()
            .onStart { delay(2000) }
            .map { it.map(WasteOrderEntity::asExternalModel) }

    override fun getHistoryById(id: String): Flow<WasteOrderModel> =
        orderDao.getOrderById(id)
            .onStart { delay(2000) }
            .map { it.asExternalModel() }
}