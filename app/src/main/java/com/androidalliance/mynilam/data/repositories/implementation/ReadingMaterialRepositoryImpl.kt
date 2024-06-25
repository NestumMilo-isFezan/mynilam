package com.androidalliance.mynilam.data.repositories.implementation

import com.androidalliance.mynilam.data.dao.ReadingMaterialDao
import com.androidalliance.mynilam.data.models.ReadingMaterial
import com.androidalliance.mynilam.data.repositories.interfaces.ReadingMaterialRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class ReadingMaterialRepositoryImpl(
    private val readingMaterialDao: ReadingMaterialDao
): ReadingMaterialRepository {
    override suspend fun insertMaterial(readingMaterial: ReadingMaterial) {
        readingMaterialDao.insertMaterial(readingMaterial)
    }

    override suspend fun updateMaterial(readingMaterial: ReadingMaterial) {
        readingMaterialDao.updateMaterial(readingMaterial)
    }

    override fun getAllMaterials(): Flow<List<ReadingMaterial>> {
        return readingMaterialDao.getAllMaterials()
    }

    override fun getMaterialById(id: Int): ReadingMaterial? {
        return readingMaterialDao.getMaterialById(id)
    }

    override suspend fun deleteMaterial(readingMaterial: ReadingMaterial) {
        readingMaterialDao.deleteMaterial(readingMaterial)
    }

    override suspend fun getMaterialByTitle(title: String): ReadingMaterial? {
        return readingMaterialDao.getMaterialByTitle(title)
    }
}