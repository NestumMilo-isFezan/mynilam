package com.androidalliance.mynilam.data.repositories.interfaces

import com.androidalliance.mynilam.data.models.Profile
import com.androidalliance.mynilam.data.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun registerUser(user: User)
    suspend fun createProfile(profile: Profile)
    fun getAllUser(): Flow<List<User>>
    fun getUser(username: String, password: String): Flow<User>
    suspend fun createUserAndProfile(user: User, profile: Profile)
}