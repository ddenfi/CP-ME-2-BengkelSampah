package com.bengkelsampah.bengkelsampahapp.data.source.local.room.util

import androidx.room.TypeConverter
import com.bengkelsampah.bengkelsampahapp.data.source.local.entity.WasteBoxEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WasteBoxConverter {
    @TypeConverter
    fun fromWasteBox(value: List<WasteBoxEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toWasteBox(value: String): List<WasteBoxEntity> {
        return Gson().fromJson(value, object : TypeToken<List<WasteBoxEntity>>() {}.type)
    }
}