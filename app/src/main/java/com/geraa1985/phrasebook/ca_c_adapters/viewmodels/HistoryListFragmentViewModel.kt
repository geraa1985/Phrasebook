package com.geraa1985.phrasebook.ca_c_adapters.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geraa1985.phrasebook.ca_c_adapters.repositories.IWordCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HistoryListFragmentViewModel(
    private val navigation: INavigation,
    private val wordCache: IWordCache
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + Job()

    private var word: String? = null

    private val showDataLiveData = MutableLiveData<List<String>>()
    private val noSuchWordLiveData = MutableLiveData<String>()

    fun getShowDataLiveData(): LiveData<List<String>> = showDataLiveData
    fun getNoSuchWordLiveData(): LiveData<String> = noSuchWordLiveData

    fun getData(word: String) {
        this.word = word
        launch {
            if (wordCache.getWordsByChars(word).isEmpty()) {
                noSuchWordLiveData.postValue("There is no such word: $word")
            } else {
                showDataLiveData.postValue(wordCache.getWordsByChars(word))
            }
        }
    }

    fun getMeanings(word: String) {
        navigation.goToMeaningsWithWord(word)
    }

    fun backClicked(): Boolean {
        navigation.goBack()
        return true
    }

}