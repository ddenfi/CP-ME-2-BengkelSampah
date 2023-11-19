package com.bengkelsampah.bengkelsampahapp.data.source.local.room.util

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.WasteOrderDao
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.bengkelsampah.bengkelsampahapp.domain.model.asWasteOrderEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class WasteOrderCallback(
    private val provider: Provider<WasteOrderDao>
) : RoomDatabase.Callback() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch {
            populateUserWasteOrder()
        }
    }

    private fun populateUserWasteOrder() {
        provider.get().insertOrders(WasteOrderModel.dummyData.map { it.asWasteOrderEntity() })
    }
}