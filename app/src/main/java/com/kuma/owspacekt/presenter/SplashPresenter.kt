package com.kuma.owspacekt.presenter

import com.kuma.owspacekt.app.OwspaceApplication
import com.kuma.owspacekt.model.api.ApiService
import com.kuma.owspacekt.model.entity.SplashEntityJ
import com.kuma.owspacekt.util.NetUtil
import com.kuma.owspacekt.util.OkhttpImageDownloader
import com.kuma.owspacekt.util.TimeUtil
import com.orhanobut.logger.Logger
import rx.Subscriber
import rx.schedulers.Schedulers
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val view: SplashContract.View,
    private val apiService: ApiService
) :
    SplashContract.Presenter {

    init {
        Logger.d("apppp:$apiService")
    }


    override fun getSplash(deviceId: String) {
        val client = "android"
        val version = "1.3.0"
        val time = TimeUtil.getCurrentSeconds()
        apiService.getSplash(client, version, time, deviceId)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(object : Subscriber<SplashEntityJ>() {
                override fun onCompleted() {
                    Logger.e("load splash onCompleted")
                }

                override fun onError(e: Throwable?) {
                    Logger.e("load splash failed:")
                }

                override fun onNext(splashEntity: SplashEntityJ?) {
                    if (NetUtil.isWifi(OwspaceApplication.instance.applicationContext)) {
                        if(splashEntity!=null){
                            val imgs = splashEntity.images
                            for(url in imgs){
                                OkhttpImageDownloader.download(url)
                            }
                        }
                    } else {
                        Logger.i("不是WIFI环境，就不去下载图片了")
                    }
                }
            })

    }
}