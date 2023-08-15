package com.kuma.owspacekt.model.api

import com.kuma.owspacekt.model.entity.SplashEntityJ
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ApiService {

    @GET("static/picture_list.txt")
    fun getSplash(
        @Query("client") client: String,
        @Query("version") version: String,
        @Query("time")time:Long,
        @Query("device_id")deviceId:String
    ): Observable<SplashEntityJ>

    fun getList()
}