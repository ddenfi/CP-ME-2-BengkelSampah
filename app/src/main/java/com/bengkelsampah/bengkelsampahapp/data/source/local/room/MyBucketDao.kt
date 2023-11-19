package com.bengkelsampah.bengkelsampahapp.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.MyBucketEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MyBucketDao {
    @Query("SELECT * FROM MyBucket")
    fun getUserWastes(): Flow<List<MyBucketEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MyBucketEntity::class)
    fun insertUserWaste(userWaste:MyBucketEntity)

}