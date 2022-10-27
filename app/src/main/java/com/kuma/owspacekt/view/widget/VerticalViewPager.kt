package com.kuma.owspacekt.view.widget

import android.content.Context
import android.view.ViewGroup

class VerticalViewPager(context: Context) : ViewGroup(context) {
    private var mDefaultGutterSize = 0

    internal class ItemInfo {
        var `object`: Any? = null
        var position = 0
        var scrolling = false
        var heightFactor = 0f
        var offset = 0f
    }

    fun initViewPager() {
        val density = context.resources.displayMetrics.density
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        TODO("Not yet implemented")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


        val childWidthSize = measuredWidth - paddingLeft - paddingRight
    }
}