package com.geraa1985.phrasebook.di.modules

import com.geraa1985.phrasebook.MyApp
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: MyApp) {
    @Provides
    fun app(): MyApp {
        return app
    }
}