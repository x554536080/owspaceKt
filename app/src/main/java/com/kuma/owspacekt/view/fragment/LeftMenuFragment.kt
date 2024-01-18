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

class LeftMenuFragment : Fragment() {


    private val mViewAnimList = mutableListOf<View>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_left_menu, container, false)
        ButterKnife.bind(this, view)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    @OnClick(R.id.right_slide_close, R.id.search)
    fun onClick(view: View) {
        when (view.id) {
            R.id.right_slide_close -> RxBus.getInstance().postEvent(Event(1000, "closeMenu"))
//    R.id.search ->
//else->
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