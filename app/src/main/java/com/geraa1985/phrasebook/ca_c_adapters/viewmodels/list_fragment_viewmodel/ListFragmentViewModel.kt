package com.geraa1985.phrasebook.ca_c_adapters.viewmodels.list_fragment_viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geraa1985.phrasebook.ca_a_entities.DataModel
import com.geraa1985.phrasebook.ca_b_usecases.list_interactor.ListInteractor
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.INavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListFragmentViewModel(
    private val interactor: ListInteractor,
    private val navigation: INavigation
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + Job()

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

    init {
        getData("Hello")
    }

    fun getData(word: String) {
        this.word = word
        showProgressLiveData.value = true
        launch {
            if (interactor.getData(word).isEmpty()) {
                showProgressLiveData.postValue(false)
                noSuchWordLiveData.postValue("There is no such word: $word")
            } else {
                showProgressLiveData.postValue(false)
                showDataLiveData.postValue(interactor.getData(word))
            }
        }
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

}