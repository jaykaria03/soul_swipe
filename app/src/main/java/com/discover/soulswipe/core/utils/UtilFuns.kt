package com.discover.soulswipe.core.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.provider.Settings
import android.widget.Toast
import android.telephony.TelephonyManager
import java.util.*
fun isNetworkAvailable(context: Context?): Boolean {
    val connectivityManager =
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return run {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}

fun openNetworkSetting(context: Context) {
    try {
        val panelIntent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(panelIntent)
    } catch (e: Exception) {
        val panelIntent = Intent(Settings.ACTION_SETTINGS).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(panelIntent)
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun getCountryCode(context: Context): String {
    val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    val countryIso = telephonyManager.networkCountryIso
    return if (!countryIso.isNullOrEmpty()) {
        val locale = Locale("", countryIso)
        if (locale.country.equals("in",true)) "+91" else ""
    } else {
        ""
    }
}