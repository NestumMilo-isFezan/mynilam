package com.androidalliance.mynilam.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "record",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["uid"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ReadingMaterial::class,
            parentColumns = ["material_id"],
            childColumns = ["material_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Record(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "record_id")
    val recordId: Int = 0,
    val summary: String,

    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "material_id")
    val materialId: Int
)
