package com.geraa1985.phrasebook.ca_d_frameworks.ui.cicerone_navigation

import com.geraa1985.phrasebook.MyApp
import com.geraa1985.phrasebook.ca_c_adapters.presenters.INavigation
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class NavigationImpl : INavigation {

    init {
        MyApp.instance.mainGraph.inject(this)
    }

    @Inject
    lateinit var router: Router

    override fun goBack() {
        router.exit()
    }

    override fun setRootScreenToMeanigsList() {
        router.newRootScreen(FragmentScreen.meaningsListScreen())
    }

    override fun goToMeaningsListScreen() {
        router.navigateTo(FragmentScreen.meaningsListScreen())
    }
}