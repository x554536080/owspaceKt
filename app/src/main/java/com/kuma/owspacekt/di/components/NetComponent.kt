package com.kuma.owspacekt.di.components

import com.kuma.owspacekt.di.modules.NetModule
import dagger.Component

@Component(modules = [NetModule::class])
interface NetComponent {
}