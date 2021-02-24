package com.geraa1985.phrasebook.ca_c_adapters.repositories

import com.geraa1985.phrasebook.ca_a_entities.DataModel
import com.geraa1985.phrasebook.ca_b_usecases.IRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class Repository constructor(
    private val web: IWeb,
    private val networkStatus: INetworkStatus
) : IRepository, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + Job()

    override suspend fun getData(word: String): List<DataModel> =
        if (networkStatus.isConnected()) {
            web.getData(word)
        } else {
            TODO("Not yet implemented")
        }

}
