package com.geraa1985.phrasebook.di.modules

import com.geraa1985.phrasebook.ca_b_usecases.IRepository
import com.geraa1985.phrasebook.ca_c_adapters.repositories.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        RxModule::class
    ]
)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun getRepo(repo: Repository): IRepository
}