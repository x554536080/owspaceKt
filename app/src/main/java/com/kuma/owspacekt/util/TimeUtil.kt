package com.kuma.owspacekt.util

object TimeUtil {

    fun getCurrentSeconds(): Long {
        val ls = System.currentTimeMillis() / 1000
        return ls
    }
}