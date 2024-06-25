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
        return recordDao.getAllRecordByUser(userId)
    }

    override fun getRecord(recordId: Int, userId: Int): Record? {
        return recordDao.getRecord(recordId, userId)
    }

    override suspend fun insertRecord(record: Record) {
        recordDao.insertRecord(record)
    }

    override suspend fun updateRecord(record: Record) {
        recordDao.updateRecord(record)
    }

    override suspend fun deleteRecord(record: Record) {
        recordDao.deleteRecord(record)
    }
}