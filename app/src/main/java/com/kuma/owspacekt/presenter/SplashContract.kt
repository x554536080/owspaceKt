package com.kuma.owspacekt.presenter

interface SplashContract {

    interface Presenter {
        fun getSplash(deviceId: String)
    }

    interface View {}
}