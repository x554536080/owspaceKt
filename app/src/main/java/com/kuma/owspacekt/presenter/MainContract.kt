package com.kuma.owspacekt.presenter

import android.content.ClipData
import com.kuma.owspacekt.model.entity.ItemJ

interface MainContract {

    interface Presenter{
        fun getListByPage(page:Int,model:Int,pageId:String,deviceId:String,createTime:String)
        fun getRecommend(deviceId:String)
    }

    interface View{
//        fun showLoading()
//        fun dismissLoading()
//        fun showNoData()
        fun showNoMore()
        fun updateListUI(itemList:List<ItemJ>)
        fun showOnFailure()
        fun showLunar(content:String)
    }
}