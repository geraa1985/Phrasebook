package com.geraa1985.phrasebook.ca_d_frameworks.web

import com.geraa1985.phrasebook.ca_a_entities.DataModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofitData {

    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Single<List<DataModel>>

}