package com.geraa1985.phrasebook.ca_c_adapters.viewmodels.main_activity_viewmodel

import androidx.lifecycle.ViewModel
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.INavigation
import javax.inject.Inject

class MainActivityViewModel
@Inject constructor(
    private val navigation: INavigation
): ViewModel() {

    init {
        navigation.setRootScreenToMeanigsList()
    }

    fun backClicked(): Boolean {
        navigation.goBack()
        return true
    }

}