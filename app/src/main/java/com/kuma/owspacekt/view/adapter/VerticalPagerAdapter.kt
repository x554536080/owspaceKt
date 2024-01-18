package com.kuma.owspacekt.view.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kuma.owspacekt.model.entity.ItemJ
import com.kuma.owspacekt.view.fragment.MainFragment

class VerticalPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val dataList = mutableListOf<ItemJ>()

    override fun getItem(position: Int): Fragment {
        return MainFragment.instance(dataList[position])//这样有没有什么性能问题重复创建什么的
    }


    override fun getCount(): Int {
        return dataList.size
    }

    fun setDataList(data: List<ItemJ>) {
        dataList.addAll(data)//显然其实不应该取名set，就append、add什么的更符合
        notifyDataSetChanged()
    }

    fun getLastItemId(): String {
        if (dataList.size == 0) {//我是真的好奇想知道这种条件有没有可能成立发生
            return "0"
        }
        val item = dataList[dataList.size - 1]
        return item.id
    }

    fun getLastItemCreateTime(): String? {
        if (dataList.size == 0) {
            return "0"
        }
        val item = dataList[dataList.size - 1]
        return item.createTime//这个属性是不是接口参数key改过，之前项目写的是create_time
    }


}