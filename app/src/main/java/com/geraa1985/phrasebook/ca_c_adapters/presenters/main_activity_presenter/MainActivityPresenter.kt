package com.geraa1985.phrasebook.ca_c_adapters.presenters.main_activity_presenter

import com.geraa1985.phrasebook.ca_c_adapters.presenters.INavigation
import moxy.MvpPresenter
import javax.inject.Inject

class MainActivityPresenter: MvpPresenter<IMainActivityView>() {

    @Inject
    lateinit var navigation: INavigation

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        navigation.setRootScreenToMeanigsList()
    }

    fun backClicked(): Boolean {
        navigation.goBack()
        return true
    }

}