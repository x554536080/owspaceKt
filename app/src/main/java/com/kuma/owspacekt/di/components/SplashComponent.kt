package com.kuma.owspacekt.di.components

import com.kuma.owspacekt.di.modules.SplashModule
import com.kuma.owspacekt.view.activity.SplashActivity
import dagger.Component

@Component(dependencies = [NetComponent::class], modules = [SplashModule::class])
interface SplashComponent {
    fun inject(splashActivity: SplashActivity)
    //test push
}
