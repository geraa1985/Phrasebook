package com.geraa1985.phrasebook.ca_c_adapters.presenters.list_fragment_presenter

import com.geraa1985.phrasebook.ca_b_usecases.list_interactor.ListInteractor
import com.geraa1985.phrasebook.ca_c_adapters.presenters.INavigation
import com.geraa1985.phrasebook.ca_d_frameworks.rx.ISchedulerProvider
import moxy.MvpPresenter
import javax.inject.Inject

class ListFragmentPresenter(
    private val interactor: ListInteractor = ListInteractor()
) : MvpPresenter<IListFragmentView>() {

    private var word: String? = null

    @Inject
    lateinit var navigation: INavigation

    @Inject
    lateinit var scheduler: ISchedulerProvider

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getData("Hello")
    }

    private fun getData(word: String) {
        this.word = word
        viewState.showProgress()
        interactor.getData(word)
            .observeOn(scheduler.ui())
            .subscribe({
                viewState.hideProgress()
                viewState.showData(it)
            }, { error ->
                viewState.hideProgress()
                error.message?.let { viewState.showError(it) }
            })
    }

    fun reload() {
        word?.let { getData(it) }
    }

    fun backClicked(): Boolean {
        navigation.goBack()
        return true
    }
}