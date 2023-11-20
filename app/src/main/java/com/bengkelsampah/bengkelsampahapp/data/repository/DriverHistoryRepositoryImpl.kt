package com.bengkelsampah.bengkelsampahapp.data.repository

import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteOrderEntity
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.asExternalModel
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.WasteOrderDao
import com.bengkelsampah.bengkelsampahapp.domain.model.OrderStatus
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.domain.repository.DriverHistoryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class DriverHistoryRepositoryImpl @Inject constructor(
    private val orderDao: WasteOrderDao
) : DriverHistoryRepository {
    override fun getOrdersHistory(): Flow<List<WasteOrderModel>> =
        orderDao.getAllOrder()
            .map { it.map(WasteOrderEntity::asExternalModel) }
            .map { it.filter { it.status == OrderStatus.CANCELLED || it.status == OrderStatus.DONE } }

    override fun getOrderHistoryById(id: String): Flow<WasteOrderModel> =
        orderDao.getOrderById(id).onStart { delay(2000) }.map { it.asExternalModel() }

}