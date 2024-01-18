package com.kuma.owspacekt.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu
import com.kuma.owspacekt.R
import com.kuma.owspacekt.app.OwspaceApplication
import com.kuma.owspacekt.di.components.DaggerMainComponent
import com.kuma.owspacekt.di.modules.MainModule
import com.kuma.owspacekt.model.entity.Event
import com.kuma.owspacekt.model.entity.ItemJ
import com.kuma.owspacekt.model.util.PreferenceUtils
import com.kuma.owspacekt.presenter.MainContract
import com.kuma.owspacekt.presenter.MainPresenter
import com.kuma.owspacekt.util.AppUtil
import com.kuma.owspacekt.util.TimeUtil
import com.kuma.owspacekt.util.tool.RxBus
import com.kuma.owspacekt.view.adapter.VerticalPagerAdapter
import com.kuma.owspacekt.view.fragment.LeftMenuFragment
import com.kuma.owspacekt.view.fragment.RightMenuFragment
import com.kuma.owspacekt.view.widget.VerticalViewPagerJ
import com.orhanobut.logger.Logger
import rx.Subscription
import rx.functions.Action1
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @BindView(R.id.view_pager)
    internal lateinit var viewPager: VerticalViewPagerJ


    private lateinit var slidingMenu: SlidingMenu

    private lateinit var leftMenu: LeftMenuFragment

    private lateinit var rightMenu: RightMenuFragment

    @Inject
    internal lateinit var presenter: MainPresenter

    private lateinit var pagerAdapter: VerticalPagerAdapter

    private var page = 1

    private var isLoading = true

    private var mLastClickTime: Long = 0

    private lateinit var subscription: Subscription

    private lateinit var deviceId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        initMenu()
        initPage()
        deviceId = AppUtil.getDeviceId(this)
        val getLunar = PreferenceUtils
            .getPrefString(this, "getLunar", null)//其实这个就理解为上次加载日历加载的日期
        if (TimeUtil.getDate("yyyyMMdd") != getLunar) {
            loadRecommend()
        }
        loadData(1, 0, "0", "0")
    }

    private fun initMenu() {
        slidingMenu = SlidingMenu(this)
        slidingMenu.mode = SlidingMenu.LEFT_RIGHT
        slidingMenu.touchModeAbove = SlidingMenu.TOUCHMODE_FULLSCREEN//这个模式有啥作用
        slidingMenu.setFadeDegree(0.35f)//渐入渐出 todo
        slidingMenu.setFadeEnabled(true)
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT)//这个style有什么用
        slidingMenu.setMenu(R.layout.left_menu)
        leftMenu = LeftMenuFragment()
        supportFragmentManager.beginTransaction().add(R.id.left_menu, leftMenu).commit()
        slidingMenu.setSecondaryMenu(R.layout.right_menu) //todo 为啥里面都是mViewBehind但上面只能用set方法这里可以用access syntax
        rightMenu = RightMenuFragment()
        supportFragmentManager.beginTransaction().add(R.id.right_menu, rightMenu).commit()

        subscription = RxBus.getInstance().toObservable(Event::class.java).subscribe {
            slidingMenu.showContent()
        }
    }

    private fun initPage() {
        pagerAdapter = VerticalPagerAdapter(supportFragmentManager)
        DaggerMainComponent.builder()
            .mainModule(MainModule(this))
            .netComponent((applicationContext as OwspaceApplication).getNetComponent())
            .build()
            .inject(this)
        viewPager.setAdapter(pagerAdapter)//adapter不需要设置layoutManager吗
        viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position >= pagerAdapter.count - 2 && !isLoading)//为啥不能用and，还是我有什么理解有偏差...
                {//position == count - 1代表翻到最后一页，>= count -2则指已经到了倒数第二页至少
//                    if(isLoading){} 这里不可能突然又再在加载了
                    Logger.i("page=$page,getLastItemId=$pagerAdapter.getLastItemId")
                    loadData(
                        page,
                        0,
                        pagerAdapter.getLastItemId(),
                        pagerAdapter.getLastItemCreateTime() ?: ""
                    )//所以基本上很清晰，基本上没啥好疑惑然后请求也不咋会重复发起 冲突啥的，后面两个参数 就 应该是给接口用来进行验证的 //比如page为0就不需要验证后面两个参数之类的啥的
                }
            }

            override fun onPageSelected(position: Int) {
//                TODO("Not yet implemented")
            }

            override fun onPageScrollStateChanged(state: Int) {
//                TODO("Not yet implemented")
            }
        })
    }

    private fun loadData(page: Int, mode: Int, pageId: String, createTime: String) {
        isLoading = true
        presenter.getListByPage(page, mode, pageId, deviceId, createTime)
    }

    private fun loadRecommend() {
        presenter.getRecommend(deviceId)
    }

    override fun onBackPressed() {
        if (slidingMenu.isMenuShowing or slidingMenu.isSecondaryMenuShowing) {
            slidingMenu.showContent()
        } else {
            if (System.currentTimeMillis() - mLastClickTime <=
                2000L
            ) {
                super.onBackPressed()
            } else {
                mLastClickTime = System.currentTimeMillis()
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show()
            }

        }
    }


    @OnClick(R.id.left_slide, R.id.right_slide)
    fun onClick(view: View) {
        when (view.id) {
            R.id.left_slide -> {
                slidingMenu.showMenu()
                leftMenu.startAnim()
            }
            R.id.right_slide -> {
                slidingMenu.showSecondaryMenu()
                rightMenu.startAnim()
            }
        }
    }

    override fun showNoMore() {
//        TODO("Not yet implemented")
    }

    override fun updateListUI(itemList: List<ItemJ>) {
        isLoading = false
        pagerAdapter.setDataList(itemList)//add...
        page++
    }

    override fun showOnFailure() {
//        TODO("Not yet implemented")
    }

    override fun showLunar(content: String) {
//        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

//github开源客户端
//https://www.jianshu.com/p/010545ecf472
//https://gitee.com/thirtydegreesray/OpenHub/