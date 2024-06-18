package com.androidalliance.mynilam.data.repositories.implementation

import com.androidalliance.mynilam.data.dao.ProfileDao
import com.androidalliance.mynilam.data.models.Profile
import com.androidalliance.mynilam.data.repositories.interfaces.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class ProfileRepositoryImpl(
    private val profileDao: ProfileDao
) : ProfileRepository {
    override fun getProfile(id: Int): Flow<Profile> {
        try {
            return profileDao.getProfile(id)
        }catch(e: Exception){
            e.printStackTrace()
            return emptyFlow()
        }
    }

    override suspend fun updateProfile(profile: Profile) {
        try {
            profileDao.updateProfile(profile)
        }catch(e: Exception){
            e.printStackTrace()
        }
    }
}