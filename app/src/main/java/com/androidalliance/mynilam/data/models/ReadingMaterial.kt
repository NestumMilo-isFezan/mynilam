package com.androidalliance.mynilam.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reading_material")
data class ReadingMaterial(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "material_id")
    val materialId: Int = 0,

    val title: String,
    val author: String,
    val type: String,
    val genre: String,
    val publication: String,
    val publicationYear: Int
)
