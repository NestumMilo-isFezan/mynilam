package com.androidalliance.mynilam.data.repositories.interfaces

import com.androidalliance.mynilam.data.models.ReadingMaterial
import kotlinx.coroutines.flow.Flow

interface ReadingMaterialRepository {
    suspend fun insertMaterial(readingMaterial: ReadingMaterial)
    suspend fun updateMaterial(readingMaterial: ReadingMaterial)
    fun getAllMaterials(): Flow<List<ReadingMaterial>>
    fun getMaterialById(id: Int): Flow<ReadingMaterial>
    suspend fun deleteMaterial(readingMaterial: ReadingMaterial)
}