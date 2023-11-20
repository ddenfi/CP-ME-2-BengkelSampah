package com.bengkelsampah.bengkelsampahapp.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteBoxEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WasteBoxDao {
    @Query("SELECT * FROM WasteBox")
    fun getUserWastes(): Flow<List<WasteBoxEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = WasteBoxEntity::class)
    fun insertUserWaste(userWaste: WasteBoxEntity)

    @Query("SELECT * FROM WasteBox WHERE wasteId = :id")
    fun getWasteBoxItemById(id: String): Flow<WasteBoxEntity?>
}