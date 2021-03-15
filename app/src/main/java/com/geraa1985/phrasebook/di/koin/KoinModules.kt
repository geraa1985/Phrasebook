package com.geraa1985.phrasebook.di.koin

import android.content.Context
import androidx.room.Room
import com.geraa1985.phrasebook.MyApp
import com.geraa1985.phrasebook.ca_b_usecases.IRepository
import com.geraa1985.phrasebook.ca_b_usecases.list_interactor.ListInteractor
import com.geraa1985.phrasebook.ca_c_adapters.repositories.INetworkStatus
import com.geraa1985.phrasebook.ca_c_adapters.repositories.IWeb
import com.geraa1985.phrasebook.ca_c_adapters.repositories.Repository
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.*
import com.geraa1985.phrasebook.ca_d_frameworks.imgLoader.GlideImgLoader
import com.geraa1985.phrasebook.ca_d_frameworks.web.IRetrofitData
import com.geraa1985.phrasebook.ca_d_frameworks.web.NetworkStatus
import com.geraa1985.phrasebook.ca_d_frameworks.web.WebData
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

val navigationModule = module {
    single { Cicerone.create() } bind Cicerone::class
    single { get<Cicerone<Router>>().navigatorHolder } bind NavigatorHolder::class
    single { get<Cicerone<Router>>().router } bind Router::class
    single { com.geraa1985.phrasebook.ca_d_frameworks.cicerone_navigation.NavigationImpl(get()) } bind INavigation::class
}

val networkModule = module {
    val baseUrl = "https://dictionary.skyeng.ru/api/public/v1/"
    val statusUrl = "https://skyeng.ru/"
    single {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    } bind Gson::class
    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
            .create(IRetrofitData::class.java)
    } bind IRetrofitData::class
    single { WebData(get()) } bind IWeb::class
    single { NetworkStatus(statusUrl) } bind INetworkStatus::class
    single { GlideImgLoader() } bind ILoadImage::class
}

val repositoryModule = module {
    single { Repository(get(), get()) } bind IRepository::class
}

val interactorModule = module {
    single { ListInteractor(get()) } bind ListInteractor::class
}

val vmModule = module {
    viewModel { MainActivityViewModel(get()) }
    viewModel { ListFragmentViewModel(get(), get(), get()) }
    viewModel { HistoryListFragmentViewModel(get(), get()) }
}

val appModule = module {
    single(named(APP_CONTEXT)) { MyApp.instance } bind Context::class
}

val cacheModule = module {
    single {
        Room.databaseBuilder(
            get(named(APP_CONTEXT)),
            com.geraa1985.cache.room.appdb.AppDB::class.java,
            com.geraa1985.cache.room.appdb.AppDB.NAME_DB
        ).build()
    } bind com.geraa1985.cache.room.appdb.AppDB::class
    single { com.geraa1985.cache.room.caches.WordCache(get()) } bind com.geraa1985.cache.IWordCache::class
}