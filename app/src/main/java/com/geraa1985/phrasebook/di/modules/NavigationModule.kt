package com.geraa1985.phrasebook.di.modules

import com.geraa1985.phrasebook.ca_c_adapters.presenters.INavigation
import com.geraa1985.phrasebook.ca_d_frameworks.ui.cicerone_navigation.NavigationImpl
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {

    @Singleton
    @Provides
    fun cicerone(): Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun navigatorHolder(cicerone: Cicerone<Router>) = cicerone.navigatorHolder

    @Singleton
    @Provides
    fun router(cicerone: Cicerone<Router>) = cicerone.router

    @Singleton
    @Provides
    fun navigation(): INavigation = NavigationImpl()

}