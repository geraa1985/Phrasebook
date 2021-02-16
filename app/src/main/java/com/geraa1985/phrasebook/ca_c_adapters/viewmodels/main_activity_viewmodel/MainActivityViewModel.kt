package com.geraa1985.phrasebook.ca_c_adapters.viewmodels.main_activity_viewmodel

import androidx.lifecycle.ViewModel
import com.geraa1985.phrasebook.MyApp
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.INavigation
import javax.inject.Inject

class MainActivityViewModel: ViewModel() {

    @Inject
    lateinit var navigation: INavigation

    init {
        MyApp.instance.mainGraph.inject(this)
        navigation.setRootScreenToMeanigsList()
    }

    fun backClicked(): Boolean {
        navigation.goBack()
        return true
    }

}