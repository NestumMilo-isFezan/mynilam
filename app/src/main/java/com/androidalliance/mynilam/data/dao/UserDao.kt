package com.androidalliance.mynilam.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidalliance.mynilam.data.models.User

@Dao
interface UserDao {

    // Register User
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun registerUser(user: User)

    // Get a User
    @Query("SELECT * FROM user WHERE uid = :id")
    fun getUser(id: Int): LiveData<User>


}