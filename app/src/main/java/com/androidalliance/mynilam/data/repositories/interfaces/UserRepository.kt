package com.androidalliance.mynilam.data.repositories.interfaces

import com.androidalliance.mynilam.data.models.Profile
import com.androidalliance.mynilam.data.models.User

interface UserRepository {
    suspend fun registerUser(user: User): Long
    suspend fun createProfile(profile: Profile)
    fun loginUser(username: String, password: String): User?
    suspend fun createUserAndProfile(user: User)
    suspend fun isUsernameExists(username: String): Boolean
    suspend fun isUserExists(email: String, password: String): Boolean
    suspend fun getUserSession(user: User): Int?
    suspend fun getUserById(userId: Int): User?
}