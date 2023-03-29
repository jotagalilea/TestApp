package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.applicationModule
import com.example.myapplication.di.dataModule
import com.example.myapplication.di.dataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(applicationModule, dataModule, dataSourceModule))
        }
    }

}