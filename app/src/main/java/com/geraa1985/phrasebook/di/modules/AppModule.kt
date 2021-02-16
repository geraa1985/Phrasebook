package com.geraa1985.phrasebook.di.modules

import com.geraa1985.phrasebook.MyApp
import com.geraa1985.phrasebook.ca_b_usecases.list_interactor.ListInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: MyApp) {
    @Provides
    fun app(): MyApp {
        return app
    }

    @Singleton
    @Provides
    fun getListInteractor(): ListInteractor = ListInteractor()
}