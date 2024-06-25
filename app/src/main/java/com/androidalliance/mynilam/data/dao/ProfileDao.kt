package com.androidalliance.mynilam.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.androidalliance.mynilam.data.models.Profile
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile where user_id = :userId") // Get Logged In User Profile
    fun getProfile(userId: Int): Profile?

    @Update(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun updateProfile(profile: Profile)
}