package com.androidalliance.mynilam.data.repositories.implementation

import com.androidalliance.mynilam.data.dao.UserDao
import com.androidalliance.mynilam.data.models.Profile
import com.androidalliance.mynilam.data.models.User
import com.androidalliance.mynilam.data.repositories.interfaces.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

class UserRepositoryImpl(
    val userDao: UserDao
) : UserRepository {
    override suspend fun registerUser(user: User) {
        try{
            userDao.registerUser(user)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun createProfile(profile: Profile) {
        try{
            userDao.createProfile(profile)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun getAllUser(): Flow<List<User>> {
        try{
            return userDao.getAllUser()
        }catch (e: Exception){
            e.printStackTrace()
            return flowOf(emptyList())
        }
    }

    override fun getUser(username: String, password: String): Flow<User> {
        try{
            return userDao.getUser(username, password)
        }catch (e: Exception){
            e.printStackTrace()
            return emptyFlow()
        }
    }

    override suspend fun createUserAndProfile(user: User, profile: Profile) {
        try{
            userDao.createUserAndProfile(user, profile)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}