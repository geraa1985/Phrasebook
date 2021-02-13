package com.geraa1985.phrasebook.ca_b_usecases

import com.geraa1985.phrasebook.ca_a_entities.DataModel
import io.reactivex.rxjava3.core.Single

interface IRepository {
    fun getData(word: String): Single<List<DataModel>>
}