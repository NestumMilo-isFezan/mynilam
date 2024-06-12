package com.androidalliance.mynilam.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "review")
data class Review(
    @PrimaryKey(autoGenerate = true)
    val reviewId: Int,
    val comment: String,
    val star: Int,

    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "material_id")
    val materialId: Int
)
