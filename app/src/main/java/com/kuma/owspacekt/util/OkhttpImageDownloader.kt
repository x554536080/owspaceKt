package com.kuma.owspacekt.util

import com.kuma.owspacekt.model.util.HttpUtils
import com.orhanobut.logger.Logger
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.FileOutputStream
import java.io.IOException

object OkhttpImageDownloader {
    fun download(url: String) {
        val request = Request.Builder().url(url).build()
        HttpUtils.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Logger.d(e)
            }

            override fun onResponse(call: Call, response: Response) {
                FileUtil.createSdDir()
                val url = response.request().url().toString()//虽然但是 获取文件名也不需要response的Url吧
                val index = url.lastIndexOf("/")
                val pictureName = url.substring(index + 1)
                if (FileUtil.isFileExist(pictureName)){
                    return
                }
                Logger.i("pictureName=$pictureName")
                val fos = FileOutputStream(FileUtil.createFile(pictureName))
                val `in` = response.body()!!.byteStream()
                val buf = ByteArray(1024)
                var len = 0
                //while((len = `in`.read(buf)) != -1)    In Java, assignments are expressions while in kotlin are not
                while(`in`.read(buf).also { len = it }!=-1){//还是有点不一样的 这里是把read返回值用来判断，额上面其实我也不知道
                    fos.write(buf,0,len)
                }
                fos.flush()//这个或许是必要的，最后一个while判断跳出后，fos的write异步操作可能还没执行完
                `in`.close()
                fos.close()
            }
        })

    }
}