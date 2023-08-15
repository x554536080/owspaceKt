package com.kuma.owspacekt.view.widget

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView


@SuppressLint("AppCompatCustomView")
class FixedImageView : ImageView {

    private var mScreenHeight: Int = 0

    constructor(paramContext: Context) : this(paramContext, null)

    constructor(paramContext: Context, paramAttributeSet: AttributeSet?) : super(
        paramContext,
        paramAttributeSet
    ) {
        init(paramContext, paramAttributeSet)
    }

//    constructor(paramContext: Context, paramAttributeSet: AttributeSet, paramInt: Int) : super(
//        paramContext,
//        paramAttributeSet,
//        paramInt
//    )


    //原项目中使用的是static方法
//    companion object{
    fun getScreenWidthHeight(paramContext: Context?): IntArray {
        val arrayOfInt = IntArray(2)
        if (paramContext == null) return arrayOfInt
        val localDisplayMetrics = DisplayMetrics()
        (paramContext as Activity).windowManager.defaultDisplay.getMetrics(localDisplayMetrics)
        val i = localDisplayMetrics.widthPixels
        val j = localDisplayMetrics.heightPixels
        arrayOfInt[0] = i
        arrayOfInt[1] = j
        return arrayOfInt
    }
//    }


    private fun init(paramContext: Context, paramAttributeSet: AttributeSet?) {
        this.mScreenHeight = getScreenWidthHeight(paramContext)[1]
    }

    override fun onMeasure(paramInt1: Int, paramInt2: Int) {
        val i = View.MeasureSpec.getSize(paramInt1)
//        View.MeasureSpec.getSize(paramInt1)
        setMeasuredDimension(
            i,
            mScreenHeight
        )//todo 为什么不直接使用match parent呢，scrollView的值难道不是screen的height吗
    }
}