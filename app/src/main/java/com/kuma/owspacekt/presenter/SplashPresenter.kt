package com.kuma.owspacekt.presenter

import com.kuma.owspacekt.model.api.ApiService

class SplashPresenter : SplashContract.Presenter {
    private lateinit var view: SplashContract.View
    private lateinit var apiService: ApiService


    override fun getSplash(deviceId: String) {


    }
}