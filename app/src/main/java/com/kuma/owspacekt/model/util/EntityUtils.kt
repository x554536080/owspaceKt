package com.kuma.owspacekt.model.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.joda.time.DateTime

object EntityUtils {
    val gson: Gson = GsonBuilder()
        .registerTypeAdapter(DateTime::class.java, DateTimeAdapter())//todo
        .create()
}