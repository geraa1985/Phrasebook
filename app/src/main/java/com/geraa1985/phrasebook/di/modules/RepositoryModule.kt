package com.geraa1985.phrasebook.di.modules

import com.geraa1985.phrasebook.ca_b_usecases.IRepository
import com.geraa1985.phrasebook.ca_c_adapters.repositories.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun repo():IRepository = Repository()
}