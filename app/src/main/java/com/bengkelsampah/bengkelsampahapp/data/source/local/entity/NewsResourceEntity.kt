package com.bengkelsampah.bengkelsampahapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NewsResources")
data class NewsResourceEntity(
    @PrimaryKey
    val newsId: String,
    val title: String,
    val date: String,
    val content: String,
    val imageUrl:String
)
