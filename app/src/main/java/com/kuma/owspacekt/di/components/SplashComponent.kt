package com.kuma.owspacekt.di.components

import com.kuma.owspacekt.di.modules.SplashModule
import com.kuma.owspacekt.view.activity.SplashActivity
import dagger.Component

@Component(modules =[SplashModule::class], dependencies = [NetComponent::class] )
interface SplashComponent {
    fun inject(splashActivity: SplashActivity?)
    //test push
}
