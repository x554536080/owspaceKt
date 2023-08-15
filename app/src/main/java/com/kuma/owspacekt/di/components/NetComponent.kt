package com.kuma.owspacekt.di.components

import com.kuma.owspacekt.di.modules.NetModule
import com.kuma.owspacekt.model.api.ApiService
import dagger.Component

@Component(modules = [NetModule::class])
interface NetComponent {
fun getApiService():ApiService
}