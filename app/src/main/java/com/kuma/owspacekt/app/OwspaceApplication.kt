package com.kuma.owspacekt.app

import android.app.Application
import android.content.Context

class OwspaceApplication : Application() {

    companion object {
        var instance: OwspaceApplication? = null

        fun get(context: Context): OwspaceApplication {
            return context.applicationContext as OwspaceApplication
        }


    }

    fun getInstance(): OwspaceApplication? {
        return instance
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}