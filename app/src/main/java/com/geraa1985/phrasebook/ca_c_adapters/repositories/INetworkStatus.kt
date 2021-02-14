package com.geraa1985.phrasebook.ca_c_adapters.repositories

import io.reactivex.rxjava3.core.Single

interface INetworkStatus {
    fun checkConnection(): Single<Boolean>
}