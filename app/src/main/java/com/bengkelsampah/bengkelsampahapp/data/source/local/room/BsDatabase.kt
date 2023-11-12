package com.bengkelsampah.bengkelsampahapp.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.UserWasteEntity


@Database(
    entities = [UserWasteEntity::class] ,
    version = 1,
    exportSchema = true
)
abstract class BsDatabase:RoomDatabase() {
    abstract fun keranjangkuDao():KeranjangkuDao
}