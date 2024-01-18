package com.kuma.owspacekt.model.util

import android.content.Context
import android.preference.PreferenceManager

object PreferenceUtils {

    fun getPrefString(context: Context, key: String, defaultValue: String?): String? {
        val settings = PreferenceManager.getDefaultSharedPreferences(context)
        return settings.getString(key, defaultValue)
    }

    fun setPrefString(context: Context, key: String, value: String) {
        val settings = PreferenceManager.getDefaultSharedPreferences(context)
        settings.edit().putString(key, value).apply()
    }


    //好像java中函数形参的final关键字在kotlin中没 没有

    fun setPrefInt(context: Context, key: String, value: Int) {
        val settings = PreferenceManager.getDefaultSharedPreferences(context)
        settings.edit().putInt(key, value).apply()
    }

    fun getPrefInt(context: Context, key: String, defaultValue: Int): Int {
        val settings = PreferenceManager.getDefaultSharedPreferences(context)
        return settings.getInt(key, defaultValue)
    }

}