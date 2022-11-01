package com.kuma.owspacekt.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import butterknife.ButterKnife
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu
import com.kuma.owspacekt.R

class MainActivity : AppCompatActivity() {

    private lateinit var slidingMenu: SlidingMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        initMenu()

    }

    private fun initMenu(){

    }
    private fun initPage(){

    }
}

//github开源客户端
//https://www.jianshu.com/p/010545ecf472
//https://gitee.com/thirtydegreesray/OpenHub/