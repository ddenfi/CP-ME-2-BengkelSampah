package com.bengkelsampah.bengkelsampahapp.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.MyBucketEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MyBucketDao {
    @Query("SELECT * FROM MyBucket")
    fun getWasteBucketItems(): Flow<List<MyBucketEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MyBucketEntity::class)
    fun insertWasteBucket(waste: MyBucketEntity)

    @Delete(entity = MyBucketEntity::class)
    fun deleteWasteBucketItem(waste: MyBucketEntity)

    @Update(entity = MyBucketEntity::class)
    fun updateWasteBucketItem(waste: MyBucketEntity)
}