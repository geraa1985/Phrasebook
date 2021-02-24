package com.geraa1985.phrasebook.ca_d_frameworks.ui.cicerone_navigation

import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.INavigation
import ru.terrakok.cicerone.Router

class NavigationImpl (private val router: Router) : INavigation {

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