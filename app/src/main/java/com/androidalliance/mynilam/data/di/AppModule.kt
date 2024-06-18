package com.androidalliance.mynilam.data.di

import android.content.Context
import com.androidalliance.mynilam.data.repositories.implementation.ProfileRepositoryImpl
import com.androidalliance.mynilam.data.repositories.implementation.ReadingMaterialRepositoryImpl
import com.androidalliance.mynilam.data.repositories.implementation.RecordRepositoryImpl
import com.androidalliance.mynilam.data.repositories.implementation.ReviewRepositoryImpl
import com.androidalliance.mynilam.data.repositories.implementation.UserRepositoryImpl
import com.androidalliance.mynilam.data.repositories.interfaces.ProfileRepository
import com.androidalliance.mynilam.data.repositories.interfaces.ReadingMaterialRepository
import com.androidalliance.mynilam.data.repositories.interfaces.RecordRepository
import com.androidalliance.mynilam.data.repositories.interfaces.ReviewRepository
import com.androidalliance.mynilam.data.repositories.interfaces.UserRepository
import com.androidalliance.mynilam.data.util.MyNilamDatabase


/* Benda yang akan menyusahkan hidup semua Android Developer
*  Kalau dorang sok keras tak nak pakai Dependency Injection Library
*  (AKA Ganja untuk terobos kegilaan semua ini)
*  Why???
*
*
*/

interface AppModule{
//    val sessionViewModel : SessionScreenModel
    val myNilamDatabase : MyNilamDatabase
    val userRepository : UserRepository
    val profileRepository : ProfileRepository
    val readingMaterialRepository : ReadingMaterialRepository
    val recordRepository : RecordRepository
    val reviewRepository : ReviewRepository
}

class AppModuleImpl(
    private val appContext: Context
) : AppModule{
    override val myNilamDatabase: MyNilamDatabase by lazy {
        MyNilamDatabase.getDatabase(context = appContext)
    }

    override val userRepository: UserRepository by lazy{
        UserRepositoryImpl(myNilamDatabase.userDao())
    }
    override val profileRepository: ProfileRepository by lazy {
        ProfileRepositoryImpl(myNilamDatabase.profileDao())
    }
    override val readingMaterialRepository: ReadingMaterialRepository by lazy{
        ReadingMaterialRepositoryImpl(myNilamDatabase.readingMaterialDao())
    }
    override val recordRepository: RecordRepository by lazy{
        RecordRepositoryImpl(myNilamDatabase.recordDao())
    }
    override val reviewRepository: ReviewRepository by lazy{
        ReviewRepositoryImpl(myNilamDatabase.reviewDao())
    }
}