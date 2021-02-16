package com.geraa1985.phrasebook.ca_c_adapters.viewmodels.list_fragment_viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geraa1985.phrasebook.MyApp
import com.geraa1985.phrasebook.ca_a_entities.DataModel
import com.geraa1985.phrasebook.ca_b_usecases.list_interactor.ListInteractor
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.INavigation
import com.geraa1985.phrasebook.ca_d_frameworks.rx.ISchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class ListFragmentViewModel : ViewModel() {

    private var word: String? = null

    private val showProgressLiveData = MutableLiveData<Boolean>()
    private val showDataLiveData = MutableLiveData<List<DataModel>>()
    private val showErrorLiveData = MutableLiveData<String>()
    private val fabSearchClickedLiveData = MutableLiveData<Unit>()
    private val noSuchWordLiveData = MutableLiveData<String>()

    fun getShowProgressLiveData(): LiveData<Boolean> = showProgressLiveData
    fun getShowDataLiveData(): LiveData<List<DataModel>> = showDataLiveData
    fun getShowErrorLiveData(): LiveData<String> = showErrorLiveData
    fun getFabSearchClickedLiveData(): LiveData<Unit> = fabSearchClickedLiveData
    fun getNoSuchWordLiveData(): LiveData<String> = noSuchWordLiveData

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var navigation: INavigation

    @Inject
    lateinit var scheduler: ISchedulerProvider

    @Inject
    lateinit var interactor: ListInteractor

    init {
        MyApp.instance.mainGraph.inject(this)
        getData("Hello")
    }

    fun getData(word: String) {
        this.word = word
        showProgressLiveData.value = true
        compositeDisposable.add(interactor.getData(word)
            .observeOn(scheduler.ui())
            .subscribe({
                showProgressLiveData.value = false
                if (it.isEmpty()) {
                    noSuchWordLiveData.value = "There is no such word: $word"
                } else {
                    showDataLiveData.value = it
                }
            }, { error ->
                showProgressLiveData.value = false
                error.message?.let { showErrorLiveData.value = it }
            }))
    }

    fun fabSearchClicked() {
        fabSearchClickedLiveData.value = Unit
    }

    fun reload() {
        word?.let { getData(it) }
    }

    fun backClicked(): Boolean {
        navigation.goBack()
        return true
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}