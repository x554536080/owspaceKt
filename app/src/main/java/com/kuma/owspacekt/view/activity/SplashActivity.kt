package com.kuma.owspacekt.view.activity

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import butterknife.ButterKnife
import com.kuma.owspacekt.R
import com.kuma.owspacekt.app.OwspaceApplication
import com.kuma.owspacekt.di.components.DaggerSplashComponent
import com.kuma.owspacekt.di.modules.SplashModule
import com.kuma.owspacekt.presenter.SplashContract
import com.kuma.owspacekt.presenter.SplashPresenter
import com.kuma.owspacekt.util.AppUtil
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashContract.View, EasyPermissions.PermissionCallbacks {
    //todo note kotlin里面怎么写是最好的。只是采取了这种写法试着写一下
//    @Inject
    var presenter: SplashPresenter? = null


    protected val needPermissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.READ_PHONE_STATE
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerSplashComponent.builder()
            .netComponent(OwspaceApplication.get(this).getNetComponent())//为啥不能.访问啊 这都咋还不知道啊
            .splashModule(SplashModule(this))
            .build().inject(this)
    }

    override fun onStart() {
        super.onStart()
        requestCodePermissions()
    }

    override fun onBackPressed() {
    }

    override fun onResume() {
        super.onResume()
    }

    private fun delaySplash() {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //todo note 这个super搞不太懂什么鬼，理解为androidx的更改和要求就好了
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    @AfterPermissionGranted(PERMISSION_REQUEST_CODE)
    fun requestCodePermissions() {
        if (!EasyPermissions.hasPermissions(this, needPermissions.toString())) {
            //todo note String数组声明时没有更好的写法吗？⬆
            EasyPermissions.requestPermissions(
                this, "应用需要这些权限", PERMISSION_REQUEST_CODE, needPermissions.toString()
            )
        } else {
            setContentView(R.layout.activity_splash)
            ButterKnife.bind(this@SplashActivity)
            delaySplash()
            val deviceId = AppUtil.getDeviceId(this)
            presenter!!.getSplash(deviceId)


        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>?) {
        recreate()//todo note 如果不调用该方法，有上面的注解，EasyPermissions的onRequestPermissionsResult方法会调用被注解的方法吗
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

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1
    }
}