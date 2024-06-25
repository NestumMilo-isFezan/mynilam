package com.androidalliance.mynilam.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.androidalliance.mynilam.data.models.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Query("SELECT * FROM record where user_id = :userId")
    fun getAllRecordByUser(userId: Int): Flow<List<Record>>
    @Query("SELECT * FROM record where record_id = :recordId and user_id = :userId")
    fun getRecord(recordId: Int, userId: Int): Record?

    @Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    suspend fun insertRecord(record: Record)
    @Update(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    suspend fun updateRecord(record: Record)
    @Delete
    suspend fun deleteRecord(record: Record)

}