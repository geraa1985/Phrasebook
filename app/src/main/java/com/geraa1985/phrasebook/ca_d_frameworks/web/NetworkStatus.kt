package com.geraa1985.phrasebook.ca_d_frameworks.web

import com.geraa1985.phrasebook.ca_c_adapters.repositories.INetworkStatus
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NetworkStatus(private val myUrl: String) : INetworkStatus {

    override suspend fun isConnected(): Boolean = suspendCoroutine { continuation ->

        Thread{
            val url = URL(myUrl)
            try {
                val httpUrlConnection = url.openConnection() as HttpURLConnection
                httpUrlConnection.connectTimeout = 3000
                httpUrlConnection.connect()
                if (httpUrlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    continuation.resume(true)
                }
            } catch (e: Exception) {
                continuation.resume(false)
            }
        }.start()

    }

}