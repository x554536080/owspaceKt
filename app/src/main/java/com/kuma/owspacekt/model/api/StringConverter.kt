package com.kuma.owspacekt.model.api

import okhttp3.ResponseBody
import retrofit2.Converter

class StringConverter:Converter<ResponseBody,String> {
    override fun convert(value: ResponseBody): String {
        return value.string()
    }
}