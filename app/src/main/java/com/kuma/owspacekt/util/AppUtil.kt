package com.kuma.owspacekt.util

import android.content.Context
import android.provider.Settings
import android.telephony.TelephonyManager
import com.kuma.owspacekt.app.OwspaceApplication

class AppUtil {
    companion object {
        fun getDeviceId(context: Context): String {
            val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            //no to do note 好像说是这玩意儿android10不能用？
            //找到这个 就用呗
            if (tm.deviceId == null) {
                //android.provider.Settings;
                val deviceId =
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID);
                return deviceId
            }
            return tm.deviceId
        }

        //todo note 自己添加的方法
        fun getAndroidID(): String? {
            val id: String = Settings.Secure.getString(
                OwspaceApplication.instance?.getContentResolver(),
                Settings.Secure.ANDROID_ID
            )
            return id ?: ""
        }
    }
}