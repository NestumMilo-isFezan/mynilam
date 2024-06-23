package com.androidalliance.mynilam.data.repositories.implementation

import com.androidalliance.mynilam.data.dao.UserDao
import com.androidalliance.mynilam.data.models.Profile
import com.androidalliance.mynilam.data.models.User
import com.androidalliance.mynilam.data.repositories.interfaces.UserRepository

class UserRepositoryImpl (
    private val userDao: UserDao
) : UserRepository {
    override suspend fun registerUser(user: User): Long {
        val insertedUserId = userDao.registerUser(user)
        return insertedUserId
    }

    override suspend fun createProfile(profile: Profile) {
        userDao.createProfile(profile)
    }

    override fun loginUser(username: String, password: String): User? {
        return userDao.loginUser(username, password)
    }

    override suspend fun createUserAndProfile(user: User) {
        val insertedUserId = userDao.registerUser(user)
        val profile = Profile(
            profileId = insertedUserId.toInt(),
            firstName = "New",
            lastName = "User",
            userId = insertedUserId.toInt(),
            motto = "Hello World"
        )
        userDao.createProfile(profile)
    }

    override suspend fun isUsernameExists(username: String): Boolean {
        return userDao.isUsernameExisted(username)
    }

    override suspend fun isUserExists(email: String, username: String): Boolean {
        return userDao.isUserExists(email, username)
    }

    override suspend fun getUserSession(user: User): Int? {
        return user.uid
    }
}