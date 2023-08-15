package com.kuma.owspacekt.app

import android.app.Application
import android.content.Context
import com.kuma.owspacekt.di.components.DaggerNetComponent
import com.kuma.owspacekt.di.components.NetComponent
import com.kuma.owspacekt.di.modules.NetModule

class OwspaceApplication : Application() {

    companion object {
        lateinit var instance: OwspaceApplication

        fun get(context: Context): OwspaceApplication {
            return context.applicationContext as OwspaceApplication//这又是什么神仙写法
        }

    }

    private lateinit var netComponent: NetComponent


    override fun onCreate() {
        super.onCreate()
        instance = this
        initNet()
    }

    private fun initNet() {
        netComponent = DaggerNetComponent.builder()
            .netModule(NetModule())//1 为啥这里DNC是构建好的，SA里面DSC没有这个类 2Module的方法废弃了最新怎么用
            .build()
    }

    fun getNetComponent(): NetComponent {
        return netComponent
    }

    fun getInstance(): OwspaceApplication {
        return instance
    }

}