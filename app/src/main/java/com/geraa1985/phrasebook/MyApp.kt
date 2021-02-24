package com.geraa1985.phrasebook

import android.app.Application
import com.geraa1985.phrasebook.di.koin.*
import org.koin.android.ext.android.startKoin

class MyApp: Application() {

    companion object{
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin(this, listOf(
            navigationModule,
            networkModule,
            rxModule,
            repositoryModule,
            interactorModule,
            vmModule
        ))
    }
}