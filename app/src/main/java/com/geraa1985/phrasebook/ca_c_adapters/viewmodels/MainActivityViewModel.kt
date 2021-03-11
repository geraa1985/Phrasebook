package com.geraa1985.phrasebook.ca_c_adapters.viewmodels

import androidx.lifecycle.ViewModel

class MainActivityViewModel constructor(
    private val navigation: INavigation
): ViewModel() {

    fun start() {
        navigation.setRootScreenToMeanigsList()
    }

    fun searchWords(text: String?) {
        text?.let {
            navigation.goToHistoryScreen(it)
        }
    }

    fun backClicked(): Boolean {
        navigation.goBack()
        return true
    }

}