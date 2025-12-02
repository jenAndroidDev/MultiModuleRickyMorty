package jjcodes.network.utis

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


fun Context.isConnected(): Boolean {

    val connectivityManager = (getSystemService(Context.CONNECTIVITY_SERVICE) as?
            ConnectivityManager)

    /* This sometimes return false even when connected to a valid wifi */
    val nwInfo  = connectivityManager?.activeNetworkInfo ?: return false
    if (nwInfo.type == ConnectivityManager.TYPE_WIFI) return true

    val nw = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager.activeNetwork ?: return false
    } else {
        TODO("VERSION.SDK_INT < M")
    }
    val actNw   = connectivityManager.getNetworkCapabilities(nw) ?: return false
    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        //for other device how are able to connect with Ethernet
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        //for check internet over Bluetooth
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
        else -> false
    }
}

@Suppress("DEPRECATION")
fun ConnectivityManager.isCurrentlyConnected() = when{
    Build.VERSION.SDK_INT>= Build.VERSION_CODES.M->{
        activeNetwork?.let(::getNetworkCapabilities)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

    }
    else -> activeNetworkInfo?.isConnected

}?:false