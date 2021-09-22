package com.example.clickstask

import android.app.Application
import com.example.clickstask.features.di.newsModule
import com.example.clickstask.features.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApp)
            modules(listOf(newsModule, viewModelModule))
        }
    }
}