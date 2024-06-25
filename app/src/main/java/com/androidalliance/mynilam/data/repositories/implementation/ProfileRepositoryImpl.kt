package com.androidalliance.mynilam.data.repositories.implementation

import com.androidalliance.mynilam.data.dao.ProfileDao
import com.androidalliance.mynilam.data.models.Profile
import com.androidalliance.mynilam.data.repositories.interfaces.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class ProfileRepositoryImpl(
    private val profileDao: ProfileDao
) : ProfileRepository {
    override fun getProfile(id: Int): Profile? {
        return profileDao.getProfile(id)
    }

    override suspend fun updateProfile(profile: Profile) {
        profileDao.updateProfile(profile)
    }
}