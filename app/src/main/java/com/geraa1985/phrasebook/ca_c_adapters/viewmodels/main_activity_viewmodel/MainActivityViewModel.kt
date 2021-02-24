package com.geraa1985.phrasebook.ca_c_adapters.viewmodels.main_activity_viewmodel

import androidx.lifecycle.ViewModel
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.INavigation

class MainActivityViewModel constructor(
    private val navigation: INavigation
): ViewModel() {

    fun start() {
        navigation.setRootScreenToMeanigsList()
    }

    fun backClicked(): Boolean {
        navigation.goBack()
        return true
    }

}