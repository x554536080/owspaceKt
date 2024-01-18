package com.kuma.owspacekt.util

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    fun getCurrentSeconds(): Long {
        val ls = System.currentTimeMillis() / 1000
        return ls
    }

    fun getDate(format:String):String{
        val str = SimpleDateFormat(format, Locale.ENGLISH).format(Date())
        return str
    }

}