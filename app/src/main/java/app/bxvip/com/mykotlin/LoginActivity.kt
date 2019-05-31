package app.bxvip.com.mykotlin

import android.Manifest
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.view.KeyEvent
import android.widget.TextView
import app.bxvip.com.mykotlin.contract.LoginContract
import app.bxvip.com.mykotlin.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_login.userName
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class LoginActivity: BaseActivity() ,LoginContract.View{
    val presenter = LoginPresenter(this)
    override fun init() {
        super.init()
        login.setOnClickListener {
            login()
        }
        password.setOnEditorActionListener { v, actionId, event ->
            login()
            true
        }
    }
    fun login(){
        //隐藏软件盘
        dideSoftKeyBord()
        //查看是否有动态分配的权限
        if(hasWriteExtraStoragePermission()){
            val userNameString = userName.text.trim().toString()
            val passwordString = password.text.trim().toString()
            presenter.login(userNameString,passwordString)
        }else{
            applyWriteExteraPermission()
        }

    }
    //申请权限
    private fun applyWriteExteraPermission() {
        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this,permissions,0)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            //用户点击的同意的权限
            login()
        }else{
            //用户没有点击同意
            toast(R.string.permission_denied)
        }
    }

    private fun hasWriteExtraStoragePermission(): Boolean {
       val result =  ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    override fun pnUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }
    override fun onPasswordError() {
        userName.error = getString(R.string.password_error)
    }

    override fun onStartLogin() {
        //开始登陆弹出登陆进度条
        showPregress(getString(R.string.logging))
    }

    override fun onLoginSuccess() {
        //隐藏进度条
        dismissProgress()
        //进入主界面
        startActivity<MainActivity>()
        finish()
    }

    override fun onLoginFiald() {
        //隐藏进度条
        dismissProgress()
        //弹出Toast
        toast(R.string.login_failed)
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }
}