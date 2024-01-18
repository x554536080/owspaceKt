package com.kuma.owspacekt.app

import android.app.Application
import android.content.Context
import com.kuma.owspacekt.R
import com.kuma.owspacekt.di.components.DaggerNetComponent
import com.kuma.owspacekt.di.components.NetComponent
import com.kuma.owspacekt.di.modules.NetModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

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
        initLogger()
        initNet()
        initDatabase()
        initTypeFace()
    }


    private fun initTypeFace() {
        val calligraphyConfig = CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/PMingLiU.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build()
        CalligraphyConfig.initDefault(calligraphyConfig)

    }

    private fun initLogger() {
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    private fun initNet() {
        netComponent = DaggerNetComponent.builder()
            .netModule(NetModule())//1 为啥这里DNC是构建好的，SA里面DSC没有这个类 2Module的方法废弃了最新怎么用
            .build()


        //其实某种角度来说还不是很清楚把这个实例放在application里面的原因

    }

    private fun initDatabase() {

    }


    fun getNetComponent(): NetComponent {
        return netComponent
    }

    fun getInstance(): OwspaceApplication {
        return instance
    }

}