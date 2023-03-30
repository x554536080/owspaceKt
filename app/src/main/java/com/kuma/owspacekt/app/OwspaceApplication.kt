package com.kuma.owspacekt.app

import android.app.Application
import android.content.Context
import com.kuma.owspacekt.di.components.DaggerNetComponent
import com.kuma.owspacekt.di.components.NetComponent
import com.kuma.owspacekt.di.modules.NetModule

class OwspaceApplication : Application() {

    companion object {
        var instance: OwspaceApplication? = null

        fun get(context: Context): OwspaceApplication {
            return context.applicationContext as OwspaceApplication
        }

    }

    private var netComponent: NetComponent? = null

    fun getInstance(): OwspaceApplication? {
        return instance
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        initNet()
    }

    private fun initNet() {
        netComponent = DaggerNetComponent.builder()
            .netModule(NetModule())
            .build()
        //其实某种角度来说还不是很清楚把这个实例放在application里面的原因
    }

    fun getNetComponent():NetComponent?{
        return netComponent
    }
}