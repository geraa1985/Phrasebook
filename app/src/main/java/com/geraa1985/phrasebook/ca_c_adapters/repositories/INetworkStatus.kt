package com.geraa1985.phrasebook.ca_c_adapters.repositories

interface INetworkStatus {
    suspend fun isConnected(): Boolean
}