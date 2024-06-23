package com.androidalliance.mynilam.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.androidalliance.mynilam.data.models.Profile
import com.androidalliance.mynilam.data.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // Create User
    suspend fun registerUser(user: User): Long
    @Insert(onConflict = OnConflictStrategy.IGNORE) // Create Profile
    suspend fun createProfile(profile: Profile)

    @Query("SELECT * FROM user WHERE username = :username AND password = :password") // Get A User
    fun loginUser(username: String, password: String): User?
    @Query("SELECT * FROM user WHERE username = :username") // Get A User
    fun isUsernameExisted(username: String): Boolean
    @Query("SELECT * FROM user WHERE email = :email OR username = :username") // Get A User
    fun isUserExists(email: String, username: String): Boolean
}