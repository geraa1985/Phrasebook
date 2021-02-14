package com.geraa1985.phrasebook.ca_c_adapters.presenters.list_fragment_presenter

import com.geraa1985.phrasebook.ca_a_entities.DataModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IListFragmentView: MvpView {
    fun showProgress()
    fun hideProgress()
    fun showData(meaningsList: List<DataModel>)
    fun showError(errorMessage: String)
    fun fabSearchClicked()
    fun noSuchWord(message: String)
}