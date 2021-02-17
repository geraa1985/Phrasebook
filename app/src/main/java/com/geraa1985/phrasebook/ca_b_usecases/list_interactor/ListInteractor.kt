package com.geraa1985.phrasebook.ca_b_usecases.list_interactor

import com.geraa1985.phrasebook.ca_a_entities.DataModel
import com.geraa1985.phrasebook.ca_b_usecases.IRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ListInteractor
@Inject constructor(
    private val repository: IRepository
) {
    fun getData(word: String): Single<List<DataModel>> = repository.getData(word)
}