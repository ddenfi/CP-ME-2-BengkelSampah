package com.bengkelsampah.bengkelsampahapp.data.source.local.room

import androidx.room.Dao
import androidx.room.Query
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.NewsResourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsResourceDao {
    @Query("SELECT * FROM NewsResources")
    fun getAllNewsResource(): Flow<List<NewsResourceEntity>>

}