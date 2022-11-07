package com.kuma.owspacekt.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

class VerticalViewPagerHandWritten(context: Context) : ViewGroup(context) {
    init {
        initViewPager()
    }

    constructor(context: Context, attrs: AttributeSet) : this(context)

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
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


        val childWidthSize = measuredWidth - paddingLeft - paddingRight
    }
}