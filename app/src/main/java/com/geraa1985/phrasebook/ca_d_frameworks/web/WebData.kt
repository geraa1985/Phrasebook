package com.geraa1985.phrasebook.ca_d_frameworks.web

import com.geraa1985.model.DataModel
import com.geraa1985.phrasebook.ca_c_adapters.repositories.IWeb

class WebData(private val retrofit: IRetrofitData): IWeb {

    override suspend fun getData(word: String): List<com.geraa1985.model.DataModel> =
        retrofit.search(word)

}