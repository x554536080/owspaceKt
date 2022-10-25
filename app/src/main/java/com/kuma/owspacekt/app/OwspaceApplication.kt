package com.kuma.owspacekt.app

import android.app.Application
import android.content.Context

class OwspaceApplication : Application() {

    companion object {
        lateinit var instance: OwspaceApplication

        fun get(context: Context): OwspaceApplication {
            return context.applicationContext as OwspaceApplication
        }
    }
}