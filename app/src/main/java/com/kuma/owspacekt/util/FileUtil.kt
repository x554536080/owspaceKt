package com.kuma.owspacekt.util

import android.os.Environment
import com.orhanobut.logger.Logger
import java.io.File
import java.io.IOException

class FileUtil {


    companion object {

        @JvmField
        val SDPATH = Environment.getExternalStorageDirectory().absolutePath

        @JvmField
        val ADPATH = FileUtil.SDPATH + "/Owspace"

        @JvmStatic
        fun createSdDir() {
            val file = File(FileUtil.ADPATH)
            if (!file.exists()) {
                val create = file.mkdirs()
                Logger.d("create = $create")
            } else {
                if (!file.isDirectory) {
                    file.delete()   // 虽然是强行删除 但我觉得这种情况是不会有可能出啥问题的
                    file.mkdir()
                }
            }
        }

        @JvmStatic
        fun isFileExist(paramString: String): Boolean {
            if (paramString == null) return false//不写这个的话文件打开会throw exception吧，不过没提示要tc?
            val localFile = File("$ADPATH/$paramString")
            if (localFile.exists()) {
                return true
            };return false
        }

        @Throws(IOException::class)
        @JvmStatic
        fun createFile(fileName: String): File {
            val file = File(ADPATH, fileName)
            file.createNewFile()
            return file
        }


        @JvmStatic
        fun getAllAD(): List<String> {
            val file = File(ADPATH)
            val fileList = file.listFiles()
            val list = ArrayList<String>()
            if (null != fileList) {
                for (f in fileList) {
                    list.add(f.absolutePath)
                }
            }
            return list
        }


    }

}