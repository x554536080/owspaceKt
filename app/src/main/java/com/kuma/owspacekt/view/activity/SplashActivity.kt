package com.kuma.owspacekt.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kuma.owspacekt.R
import com.kuma.owspacekt.presenter.SplashPresenter
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {
    @Inject//todo 笔记，kotlin里面怎么写是最好的。只是采取了这种写法试着写一下
    var presenter: SplashPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}