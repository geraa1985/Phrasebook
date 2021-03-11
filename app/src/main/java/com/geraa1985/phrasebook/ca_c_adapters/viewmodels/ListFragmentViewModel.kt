package com.geraa1985.phrasebook.ca_c_adapters.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geraa1985.phrasebook.ca_b_usecases.list_interactor.ListInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListFragmentViewModel(
    private val interactor: ListInteractor,
    private val navigation: INavigation,
    private val wordCache: com.geraa1985.cache.IWordCache
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + Job()

    private var word: String? = null

    private val showProgressLiveData = MutableLiveData<Boolean>()
    private val showDataLiveData = MutableLiveData<List<com.geraa1985.model.DataModel>>()
    private val showErrorLiveData = MutableLiveData<String>()
    private val fabSearchClickedLiveData = MutableLiveData<Boolean>()
    private val noSuchWordLiveData = MutableLiveData<String>()

    fun getShowProgressLiveData(): LiveData<Boolean> = showProgressLiveData
    fun getShowDataLiveData(): LiveData<List<com.geraa1985.model.DataModel>> = showDataLiveData
    fun getShowErrorLiveData(): LiveData<String> = showErrorLiveData
    fun getFabSearchClickedLiveData(): LiveData<Boolean> = fabSearchClickedLiveData
    fun getNoSuchWordLiveData(): LiveData<String> = noSuchWordLiveData

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
                wordCache.addWord(word)
            }
        }
    }

    fun fabSearchClicked() {
        fabSearchClickedLiveData.value = true
    }

    fun reload() {
        word?.let { getData(it) }
    }

    fun itemClicked(word: String, translation: String?, imgUrl: String) {
        navigation.goToWordScreen(word, translation, imgUrl)
        fabSearchClickedLiveData.value = false
    }

    fun backClicked(): Boolean {
        navigation.goBack()
        return true
    }

}