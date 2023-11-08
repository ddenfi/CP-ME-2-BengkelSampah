package com.bengkelsampah.bengkelsampahapp.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.UserWasteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface KeranjangkuDao {

    @Query("SELECT * FROM KeranjangKu")
    fun getUserWastes(): Flow<List<UserWasteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserWaste(userWaste:UserWasteEntity)

}