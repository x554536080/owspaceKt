package com.kuma.owspacekt.view.activity

import android.Manifest
import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import butterknife.BindView
import butterknife.ButterKnife
import com.kuma.owspacekt.R
import com.kuma.owspacekt.app.OwspaceApplication
import com.kuma.owspacekt.di.components.DaggerSplashComponent
import com.kuma.owspacekt.di.modules.SplashModule
import com.kuma.owspacekt.model.util.PreferenceUtils
import com.kuma.owspacekt.presenter.SplashContract
import com.kuma.owspacekt.presenter.SplashPresenter
import com.kuma.owspacekt.util.AppUtil
import com.kuma.owspacekt.util.FileUtil
import com.kuma.owspacekt.view.widget.FixedImageView
import com.orhanobut.logger.Logger
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import javax.inject.Inject


/**
 * 思路整理，便于好理解这个类
 *
 * permission的部分，再稍微看一下 其实阅读的理解逻辑都好懂，只是从实现的角度还有些问题 以及 那个注解和结果的两个回调如何关联的
 *
 *
 *
 * **/


class SplashActivity : BaseActivity(), SplashContract.View, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.splash_img)
    lateinit var splashImage: ImageView

    //todo note kotlin里面怎么写是最好的。只是采取了这种写法试着写一下
    @Inject
    @JvmField
    var presenter: SplashPresenter? = null


    companion object {
        private const val PERMISSION_REQUEST_CODE = 1
    }

    protected val needPermissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.READ_PHONE_STATE
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerSplashComponent.builder()
            .netComponent(OwspaceApplication.get(this).getNetComponent())
            .splashModule(SplashModule(this)).build().inject(this)
        initStatus()
    }

    private fun initStatus() {
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onStart() {
        super.onStart()
//        ActivityCompat.requestPermissions(this,needPermissions, PERMISSION_REQUEST_CODE)
        requestCodePermissions()
    }

    override fun onBackPressed() {
    }

    override fun onResume() {
        super.onResume()
    }

    private fun delaySplash() {
        val picList = FileUtil.getAllAD()//file path
        if (picList.size > 0) {
            val random = java.util.Random()
            var currentIndex = random.nextInt(picList.size)
            val lastIndex = PreferenceUtils.getPrefInt(this, "splash_img_index", 0)
            Logger.i("当前（先前）imgIndex=$lastIndex")
            if (currentIndex == lastIndex) {
                if (currentIndex == picList.size - 1) currentIndex = 0 else currentIndex += 1
            }//原来是什么逻辑 xx不看了
            PreferenceUtils.setPrefInt(this, "splash_img_index", currentIndex)
            Logger.i("当前的pictureList.size=${picList.size},index = $currentIndex")//为啥要加中括号
            val file = File(picList[currentIndex])

            val fis = FileInputStream(file)
            splashImage?.setImageDrawable(IputStream2Drawable(fis))
            animWelcomeImage()
            fis.close()

        } else {
            val assetManager = assets
            val `in` = assetManager.open("welcome_default.jpg")
            splashImage?.setImageDrawable(IputStream2Drawable(`in`))
            animWelcomeImage()
            `in`.close()
        }
    }

    fun IputStream2Drawable(`in`: InputStream): Drawable {
        val drawable = BitmapDrawable.createFromStream(`in`, "splashImg")//这第二个参数好像就是没啥用
        return drawable
    }

    private fun animWelcomeImage() {
        val animator = ObjectAnimator.ofFloat(splashImage,"translationX",-100F)
        animator.setDuration(1500L).start()
        animator.addListener(object :Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                val intent = Intent(this@SplashActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }
        })

    }


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //todo note 这个super搞不太懂什么鬼，理解为androidx的更改和要求就好了
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    //    @AfterPermissionGranted(PERMISSION_REQUEST_CODE)
    fun requestCodePermissions() {
        if (!EasyPermissions.hasPermissions(this, *needPermissions)) {
            //todo note String数组声明时没有更好的写法吗？⬆
            EasyPermissions.requestPermissions(
                this, "应用需要这些权限", PERMISSION_REQUEST_CODE, *needPermissions
            )
        } else {
            initWithPermissions()
//            setContentView(R.layout.activity_splash)
//            ButterKnife.bind(this@SplashActivity)
//            delaySplash()
//            val deviceId = AppUtil.getDeviceId(this)
//            presenter?.getSplash(deviceId)
        }
    }

    fun initWithPermissions() {
        setContentView(R.layout.activity_splash)
        ButterKnife.bind(this@SplashActivity)
        delaySplash()
        val deviceId = AppUtil.getDeviceId(this)
        presenter?.getSplash(deviceId)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>?) {
        initWithPermissions()
//        recreate()//todo note 如果不调用该方法，有上面的注解，EasyPermissions的onRequestPermissionsResult方法会调用被注解的方法吗
        //todo 以及 有点没懂这个rationale的用法
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>?) {
        showMissingPermissionDialog()
    }

    private fun showMissingPermissionDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("提示")
        builder.setMessage("当前应用缺少必要权限。请点击\"设置\"-\"权限\"-打开所需权限。")
        builder.setNegativeButton("取消") { _, _ -> finish() }
        builder.setPositiveButton("设置") { _, _ -> startAppSettings() }
        builder.setCancelable(false)
        builder.show()
    }

    private fun startAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }
}