package com.geraa1985.phrasebook.ca_c_adapters.repositories

import com.geraa1985.phrasebook.MyApp
import com.geraa1985.phrasebook.ca_a_entities.DataModel
import com.geraa1985.phrasebook.ca_b_usecases.IRepository
import com.geraa1985.phrasebook.ca_d_frameworks.rx.ISchedulerProvider
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class Repository: IRepository {

    init {
        MyApp.instance.mainGraph.inject(this)
    }

    @Inject
    lateinit var web: IWeb

    @Inject
    lateinit var networkStatus: INetworkStatus

    @Inject
    lateinit var schedulers: ISchedulerProvider

    override fun getData(word: String): Single<List<DataModel>> =
        networkStatus.checkConnection().flatMap { isOnline ->
            if (isOnline) {
                web.getData(word)
            } else {
                TODO("Not yet implemented")
            }
        }.subscribeOn(schedulers.io())

}