package com.geraa1985.phrasebook.di.components

import com.geraa1985.phrasebook.ca_b_usecases.list_interactor.ListInteractor
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.list_fragment_viewmodel.ListFragmentViewModel
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.main_activity_viewmodel.MainActivityViewModel
import com.geraa1985.phrasebook.ca_c_adapters.repositories.Repository
import com.geraa1985.phrasebook.ca_d_frameworks.ui.activities.MainActivity
import com.geraa1985.phrasebook.ca_d_frameworks.ui.cicerone_navigation.NavigationImpl
import com.geraa1985.phrasebook.ca_d_frameworks.web.WebData
import com.geraa1985.phrasebook.di.modules.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NavigationModule::class,
        NetworkModule::class,
        RxModule::class,
        RepositoryModule::class
    ]
)
interface MainGraph {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivityViewModel: MainActivityViewModel)
    fun inject(navigationImpl: NavigationImpl)
    fun inject(listFragmentViewModel: ListFragmentViewModel)
    fun inject(webData: WebData)
    fun inject(repository: Repository)
    fun inject(listInteractor: ListInteractor)
}