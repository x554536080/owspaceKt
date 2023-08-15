package com.kuma.owspacekt.util

import android.content.Context
import android.net.ConnectivityManager

object NetUtil {
    fun isWifi(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm == null) return false
        return cm.activeNetworkInfo!!.type == ConnectivityManager.TYPE_WIFI
    }
}