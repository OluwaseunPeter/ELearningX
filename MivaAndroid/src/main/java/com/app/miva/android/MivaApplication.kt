package com.app.miva.android

import android.app.Application
import com.app.miva.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MivaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MivaApplication)
            modules(appModule())
        }
    }
}