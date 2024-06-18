package com.androidalliance.mynilam.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "review",
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
data class Review(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "review_id")
    val reviewId: Int,
    val comment: String,
    val star: Int,

    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "material_id")
    val materialId: Int
)
