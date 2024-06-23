package com.androidalliance.mynilam.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "profile",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["uid"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Profile(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "profile_id")
    val profileId: Int = 0,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "motto")
    val motto: String,
    @ColumnInfo(name = "user_id")
    var userId: Int
)
