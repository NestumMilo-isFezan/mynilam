package com.androidalliance.mynilam

import android.app.Application
import com.androidalliance.mynilam.data.di.AppModule
import com.androidalliance.mynilam.data.di.AppModuleImpl

class MyNilamApplication() : Application() {
    companion object{
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }

}