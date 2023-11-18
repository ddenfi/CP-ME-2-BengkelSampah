package com.bengkelsampah.bengkelsampahapp.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.MyBucketEntity
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.NewsResourceEntity
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteBoxEntity
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteOrderEntity
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteResourceEntity
import com.bengkelsampah.bengkelsampahapp.data.source.local.room.util.WasteBoxConverter


@Database(
    entities = [
        MyBucketEntity::class,
        WasteBoxEntity::class,
        WasteResourceEntity::class,
        NewsResourceEntity::class,
        WasteOrderEntity::class
    ],
    version = 1,
    exportSchema = true
)

@TypeConverters(
    WasteBoxConverter::class
)
abstract class BsDatabase : RoomDatabase() {
    abstract fun myBucketDao(): MyBucketDao
    abstract fun wasteBoxDao(): WasteBoxDao
    abstract fun wasteResourceDao(): WasteResourceDao
    abstract fun newsResourceDao(): NewsResourceDao
    abstract fun wasteOrderDao():WasteOrderDao

}