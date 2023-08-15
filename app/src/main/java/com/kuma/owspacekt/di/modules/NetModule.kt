package com.kuma.owspacekt.di.modules

import com.kuma.owspacekt.BuildConfig
import com.kuma.owspacekt.model.api.ApiService
import com.kuma.owspacekt.model.api.StringConverterFactory
import com.kuma.owspacekt.model.util.EntityUtils
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetModule {


    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY //todo
        else HttpLoggingInterceptor.Level.NONE
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
        return okHttpClient

    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://static.owsapce.com/")
            .addConverterFactory(StringConverterFactory.create())//todo
            .addConverterFactory(GsonConverterFactory.create(EntityUtils.gson))//todo
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
        return retrofit
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}