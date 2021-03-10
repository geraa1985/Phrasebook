package com.geraa1985.phrasebook.ca_d_frameworks.web

import com.geraa1985.phrasebook.ca_a_entities.DataModel
import com.geraa1985.phrasebook.ca_c_adapters.repositories.IWeb

class WebData(private val retrofit: IRetrofitData): IWeb {

    override suspend fun getData(word: String): List<DataModel> =
        retrofit.search(word)

}