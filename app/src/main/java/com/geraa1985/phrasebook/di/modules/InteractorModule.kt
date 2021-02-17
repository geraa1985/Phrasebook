package com.geraa1985.phrasebook.di.modules

import com.geraa1985.phrasebook.ca_b_usecases.IRepository
import com.geraa1985.phrasebook.ca_b_usecases.list_interactor.ListInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    @Singleton
    @Provides
    fun getListInteractor(repo: IRepository): ListInteractor = ListInteractor(repo)

}