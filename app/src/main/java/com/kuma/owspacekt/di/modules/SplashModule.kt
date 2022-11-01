package com.kuma.owspacekt.di.modules

import com.kuma.owspacekt.presenter.SplashContract
import dagger.Module

@Module
class SplashModule(
    private val view: SplashContract.View
) {

    fun provideView(): SplashContract.View {
        return this.view
    }
}