package com.geraa1985.phrasebook.ca_c_adapters.repositories

import com.geraa1985.phrasebook.ca_a_entities.DataModel
import io.reactivex.rxjava3.core.Single

interface IWeb {
    fun getData(word: String): Single<List<DataModel>>
}