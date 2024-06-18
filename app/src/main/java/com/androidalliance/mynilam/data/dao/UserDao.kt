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

    @Query("SELECT * FROM user, profile") // Get User List
    fun getAllUser(): Flow<List<User>>
    @Query("SELECT * FROM user WHERE username = :username AND password = :password") // Get A User
    fun getUser(username: String, password: String): Flow<User>

    @Transaction
    suspend fun createUserAndProfile(user: User, profile: Profile) {
        val getUserId = registerUser(user)
        profile.userId = getUserId.toInt()
        createProfile(profile)
    }

//    // Register User
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun registerUser(user: User)
//
//    // Get a User
//    @Query("SELECT * FROM user WHERE uid = :id")
//    fun getUser(id: Int): LiveData<User>
}