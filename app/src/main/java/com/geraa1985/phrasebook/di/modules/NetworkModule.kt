package com.geraa1985.phrasebook.di.modules

import com.geraa1985.phrasebook.ca_c_adapters.repositories.IWeb
import com.geraa1985.phrasebook.ca_c_adapters.repositories.INetworkStatus
import com.geraa1985.phrasebook.ca_d_frameworks.web.NetworkStatus
import com.geraa1985.phrasebook.ca_d_frameworks.web.IRetrofitData
import com.geraa1985.phrasebook.ca_d_frameworks.web.WebData
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://dictionary.skyeng.ru/api/public/v1/"

    @Named("statusUrl")
    @Provides
    fun statusUrl(): String = "https://skyeng.ru/"

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Singleton
    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): IRetrofitData =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IRetrofitData::class.java)

    @Singleton
    @Provides
    fun webData():IWeb = WebData()

    @Singleton
    @Provides
    fun networkStatus(@Named("statusUrl") statusUrl: String): INetworkStatus = NetworkStatus(statusUrl)
}