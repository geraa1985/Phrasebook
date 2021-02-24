package com.geraa1985.phrasebook.di.koin

import com.geraa1985.phrasebook.ca_b_usecases.IRepository
import com.geraa1985.phrasebook.ca_b_usecases.list_interactor.ListInteractor
import com.geraa1985.phrasebook.ca_c_adapters.repositories.INetworkStatus
import com.geraa1985.phrasebook.ca_c_adapters.repositories.IWeb
import com.geraa1985.phrasebook.ca_c_adapters.repositories.Repository
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.INavigation
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.list_fragment_viewmodel.ListFragmentViewModel
import com.geraa1985.phrasebook.ca_c_adapters.viewmodels.main_activity_viewmodel.MainActivityViewModel
import com.geraa1985.phrasebook.ca_d_frameworks.rx.ISchedulerProvider
import com.geraa1985.phrasebook.ca_d_frameworks.rx.SchedulerProvider
import com.geraa1985.phrasebook.ca_d_frameworks.ui.cicerone_navigation.NavigationImpl
import com.geraa1985.phrasebook.ca_d_frameworks.web.IRetrofitData
import com.geraa1985.phrasebook.ca_d_frameworks.web.NetworkStatus
import com.geraa1985.phrasebook.ca_d_frameworks.web.WebData
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

val navigationModule = module {
    single { Cicerone.create() } bind Cicerone::class
    single { get<Cicerone<Router>>().navigatorHolder } bind NavigatorHolder::class
    single { get<Cicerone<Router>>().router } bind Router::class
    single { NavigationImpl(get()) } bind INavigation::class
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
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
            .create(IRetrofitData::class.java)
    } bind IRetrofitData::class
    single { WebData(get()) } bind IWeb::class
    single { NetworkStatus(statusUrl) } bind INetworkStatus::class
}

val rxModule = module {
    single { SchedulerProvider() } bind ISchedulerProvider::class
}

val repositoryModule = module {
    single { Repository(get(), get(), get()) } bind IRepository::class
}

val interactorModule = module {
    single { ListInteractor(get()) } bind ListInteractor::class
}

val vmModule = module {
    viewModel { MainActivityViewModel(get()) }
    viewModel { ListFragmentViewModel(get(),get(),get()) }
}