package com.kuma.owspacekt.presenter

import com.google.gson.JsonParser
import com.kuma.owspacekt.model.api.ApiService
import com.kuma.owspacekt.model.entity.ResultData.ResultDataInherited
import com.kuma.owspacekt.util.TimeUtil
import com.orhanobut.logger.Logger
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val view: MainContract.View,
    private val apiService: ApiService
) : MainContract.Presenter {

    init {
        Logger.d("apppp:$apiService")
    }


    override fun getListByPage(
        page: Int,
        model: Int,
        pageId: String,
        deviceId: String,
        createTime: String
    ) {
        apiService.getList(
            "api", "getList", page, model, pageId, createTime,
            "android", "1.3.0", TimeUtil.getCurrentSeconds(), deviceId, 1
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<ResultDataInherited>() {
                override fun onCompleted() {
//                    TODO("Not yet implemented")
                }

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                    view.showOnFailure()
                }

                override fun onNext(t: ResultDataInherited?) {
                    val size = t?.datas?.size ?: 0
                    if (size > 0) {
                        view.updateListUI(t!!.datas)//有啥必要要打assert啊
                    } else {
                        view.showNoMore()
                    }
                }
            })
    }

    override fun getRecommend(deviceId: String) {
//        val key = TimeUtil.getDate("yyyyMMdd")
//        Logger.e("getRecommend key:$key")
        apiService.getRecommend("home","Api","getLunar","android",deviceId,deviceId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Subscriber<String>(){
                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {
                    Logger.e("onError:")
                    e?.printStackTrace()
                }

                override fun onNext(t: String?) {
                    val key = TimeUtil.getDate("yyyyMMdd")

                    val jsonParser = JsonParser()
                    val jel = jsonParser.parse(t)
                    var jsonObject = jel.asJsonObject//todo 这个操作是什么含义和意义
                    jsonObject = jsonObject.getAsJsonObject("datas")
                    jsonObject = jsonObject.getAsJsonObject(key)
                    view.showLunar(jsonObject.get("thumbnail").asString)
                }
            })
    }
}