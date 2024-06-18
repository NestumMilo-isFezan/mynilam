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
        try{
            readingMaterialDao.insertMaterial(readingMaterial)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun updateMaterial(readingMaterial: ReadingMaterial) {
        try{
            readingMaterialDao.updateMaterial(readingMaterial)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun getAllMaterials(): Flow<List<ReadingMaterial>> {
        try{
            return readingMaterialDao.getAllMaterials()
        }catch (e: Exception){
            e.printStackTrace()
            return emptyFlow()
        }
    }

    override fun getMaterialById(id: Int): Flow<ReadingMaterial> {
        try{
            return readingMaterialDao.getMaterialById(id)
        }catch (e: Exception){
            e.printStackTrace()
            return emptyFlow()
        }
    }

    override suspend fun deleteMaterial(readingMaterial: ReadingMaterial) {
        try{
            readingMaterialDao.deleteMaterial(readingMaterial)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}