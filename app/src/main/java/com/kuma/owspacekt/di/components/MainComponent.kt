package com.kuma.owspacekt.di.components

import com.kuma.owspacekt.di.modules.MainModule
import com.kuma.owspacekt.view.activity.MainActivity
import dagger.Component

@Component(modules = [MainModule::class], dependencies = [NetComponent::class])
interface MainComponent {

    fun inject(activity: MainActivity)
}