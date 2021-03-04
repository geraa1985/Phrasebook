package com.geraa1985.phrasebook.ca_c_adapters.viewmodels

interface INavigation {
    fun goBack()
    fun setRootScreenToMeanigsList()
    fun goToWordScreen(word: String, translation: String?, imgUrl: String)
    fun goToHistoryScreen(word: String)
}