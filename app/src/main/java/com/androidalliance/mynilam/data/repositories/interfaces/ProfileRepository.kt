package com.androidalliance.mynilam.data.repositories.interfaces

import com.androidalliance.mynilam.data.models.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfile(id: Int): Flow<Profile>
    suspend fun updateProfile(profile: Profile)
}