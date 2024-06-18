package com.androidalliance.mynilam.data.repositories.implementation

import com.androidalliance.mynilam.data.dao.RecordDao
import com.androidalliance.mynilam.data.models.Record
import com.androidalliance.mynilam.data.repositories.interfaces.RecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

class RecordRepositoryImpl(
    private val recordDao: RecordDao
) : RecordRepository {
    override fun getAllRecordByUser(userId: Int): Flow<List<Record>> {
        try{
            return recordDao.getAllRecordByUser(userId)
        }catch (e:Exception){
            e.printStackTrace()
            return flowOf(emptyList())
        }
    }

    override fun getRecord(recordId: Int, userId: Int): Flow<Record> {
        try{
            return recordDao.getRecord(recordId, userId)
        }catch (e:Exception){
            e.printStackTrace()
            return emptyFlow()
        }
    }

    override suspend fun insertRecord(record: Record) {
        try{
            recordDao.insertRecord(record)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override suspend fun updateRecord(record: Record) {
        try{
            recordDao.updateRecord(record)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override suspend fun deleteRecord(record: Record) {
        try{
            recordDao.deleteRecord(record)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}