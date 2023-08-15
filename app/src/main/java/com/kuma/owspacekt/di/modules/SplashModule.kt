package com.kuma.owspacekt.di.modules

import com.kuma.owspacekt.presenter.SplashContract
import dagger.Module
import dagger.Provides

@Module
class SplashModule(
    private val view: SplashContract.View
) {

    @Provides
    fun provideView(): SplashContract.View {
        return this.view
    }
}