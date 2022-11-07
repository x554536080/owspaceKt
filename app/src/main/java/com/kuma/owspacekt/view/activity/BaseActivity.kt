package com.kuma.owspacekt.view.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

open class BaseActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun attachBaseContext(newBase: Context?) {
        //todo note 虽然不知道是什么原理，但就先这么用吧
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}