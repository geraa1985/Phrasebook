package com.geraa1985.phrasebook.ca_b_usecases.list_interactor

import com.geraa1985.model.DataModel
import com.geraa1985.phrasebook.ca_b_usecases.IRepository

class ListInteractor constructor(
    private val repository: IRepository
) {
    suspend fun getData(word: String): List<com.geraa1985.model.DataModel> = repository.getData(word)
}