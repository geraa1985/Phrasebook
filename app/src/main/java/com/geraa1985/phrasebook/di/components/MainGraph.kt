package com.geraa1985.phrasebook.di.components

import com.geraa1985.phrasebook.ca_b_usecases.list_interactor.ListInteractor
import com.geraa1985.phrasebook.ca_c_adapters.presenters.list_fragment_presenter.ListFragmentPresenter
import com.geraa1985.phrasebook.ca_c_adapters.presenters.main_activity_presenter.MainActivityPresenter
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
    fun inject(mainActivityPresenter: MainActivityPresenter)
    fun inject(navigationImpl: NavigationImpl)
    fun inject(listFragmentPresenter: ListFragmentPresenter)
    fun inject(webData: WebData)
    fun inject(repository: Repository)
    fun inject(listInteractor: ListInteractor)
}