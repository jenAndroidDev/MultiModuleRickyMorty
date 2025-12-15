package com.rmworld.core.network.utls

import android.content.Context
import android.net.ConnectivityManager


class NetworkHelper(private val context: Context) {
    @Suppress("DEPRECATION")
    fun checkForInternet(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.isCurrentlyConnected()
    }

}