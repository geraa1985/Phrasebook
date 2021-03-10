package com.geraa1985.phrasebook

import android.app.Application
import com.geraa1985.phrasebook.di.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp: Application() {

    companion object{
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(listOf(
                navigationModule,
                networkModule,
                repositoryModule,
                interactorModule,
                vmModule,
                cacheModule,
                appModule
            ))
        }
    }
}