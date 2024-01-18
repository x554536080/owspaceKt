package com.kuma.owspacekt.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.OnClick
import com.kuma.owspacekt.R
import com.kuma.owspacekt.model.entity.Event
import com.kuma.owspacekt.util.tool.RxBus

class RightMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_right_menu, container, false)//这个参数有啥用
        ButterKnife.bind(this, view)//这个this不能省略？

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }

    @OnClick(R.id.slide_close, R.id.setting)
    fun onClick(view: View) {
        when (view.id) {
            R.id.slide_close -> RxBus.getInstance().postEvent(Event(1000, "closeMenu"))

//    R.id.setting ->
//            else ->
        }
    }

    fun startAnim() {
        startIconAnim()
        startIconAnim()
        startColumnAnim()
    }

    private fun startIconAnim() {

    }

    private fun startColumnAnim() {

    }
}