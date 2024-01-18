package com.kuma.owspacekt.di.modules

import com.kuma.owspacekt.presenter.MainContract
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val mView:MainContract.View) {

    @Provides
    fun provideMainView():MainContract.View{
        return mView
    }

}
