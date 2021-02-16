package com.geraa1985.phrasebook

import android.app.Application
import com.geraa1985.phrasebook.di.components.AppComponent
import com.geraa1985.phrasebook.di.components.DaggerAppComponent

class MyApp: Application() {

    companion object{
        lateinit var instance: MyApp
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}