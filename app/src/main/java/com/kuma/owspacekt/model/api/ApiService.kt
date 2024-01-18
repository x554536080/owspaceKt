package com.kuma.owspacekt.model.api

import com.kuma.owspacekt.model.entity.ResultData.ResultDataInherited
import com.kuma.owspacekt.model.entity.SplashEntityJ
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ApiService {

    @GET("static/picture_list.txt")
    fun getSplash(
        @Query("client") client: String,
        @Query("version") version: String,
        @Query("time") time: Long,
        @Query("device_id") deviceId: String
    ): Observable<SplashEntityJ>

    @GET("/")
    fun getList(
        @Query("c") c: String,
        @Query("a") a: String,
        @Query("p") page: Int,
        @Query("model") model: Int,
        @Query("page_id") pageId: String,
        @Query("create_time") createTime: String,
        @Query("client") client: String,
        @Query("version") version: String,
        @Query("time") time: Long,
        @Query("device_id") deviceId: String,
        @Query("show_sdv") show_sdv: Int
    ): Observable<ResultDataInherited>

    @GET("index.php")
    fun getRecommend(
        @Query("m") m: String,
        @Query("c") api: String,
        @Query("a") a: String,
        @Query("client")client: String,
        @Query("version")version: String,
        @Query("device_id")deviceId:String
    ): Observable<String>
}