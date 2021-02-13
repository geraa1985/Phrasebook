package com.geraa1985.phrasebook.ca_d_frameworks.web

import com.geraa1985.phrasebook.MyApp
import com.geraa1985.phrasebook.ca_a_entities.DataModel
import com.geraa1985.phrasebook.ca_c_adapters.repositories.IWeb
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WebData: IWeb {

    init {
        MyApp.instance.mainGraph.inject(this)
    }

    @Inject
    lateinit var retrofit: IRetrofitData

    override fun getData(word: String): Single<List<DataModel>> =
        retrofit.search(word)

}