package com.kuma.owspacekt.model.api

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class StringConverterFactory:Converter.Factory() {
companion object{
    fun create():StringConverterFactory{
        return StringConverterFactory()
    }
}

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return if(type == String::class.java){
            StringConverter()
        }else null

    }
}