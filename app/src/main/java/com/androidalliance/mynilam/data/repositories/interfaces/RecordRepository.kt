package com.androidalliance.mynilam.data.repositories.interfaces

import com.androidalliance.mynilam.data.models.Record
import kotlinx.coroutines.flow.Flow

interface RecordRepository {
    fun getAllRecordByUser(userId: Int): Flow<List<Record>>
    fun getRecord(recordId: Int, userId: Int): Record?
    suspend fun insertRecord(record: Record)
    suspend fun updateRecord(record: Record)
    suspend fun deleteRecord(record: Record)
}