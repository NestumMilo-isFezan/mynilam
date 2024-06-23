package com.androidalliance.mynilam.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reading_material")
data class ReadingMaterial(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "material_id")
    val materialId: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "genre")
    val genre: String,
    @ColumnInfo(name = "publication")
    val publication: String,
    @ColumnInfo(name = "publication_year")
    val publicationYear: Int
)
