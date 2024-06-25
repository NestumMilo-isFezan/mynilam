package com.androidalliance.mynilam.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.androidalliance.mynilam.data.models.ReadingMaterial
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadingMaterialDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMaterial(readingMaterial: ReadingMaterial)

    @Update
    suspend fun updateMaterial(readingMaterial: ReadingMaterial)

    @Query("SELECT * FROM reading_material")
    fun getAllMaterials(): Flow<List<ReadingMaterial>>
    @Query("SELECT * FROM reading_material WHERE material_id = :id")
    fun getMaterialById(id: Int): ReadingMaterial?
    @Query("SELECT * FROM reading_material WHERE title = :title")
    fun getMaterialByTitle(title: String): ReadingMaterial?

    @Delete
    suspend fun deleteMaterial(readingMaterial: ReadingMaterial)

}