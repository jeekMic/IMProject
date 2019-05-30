package app.bxvip.com.mykotlin

import android.app.ProgressDialog
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
        val userNameString = userName.text.trim().toString()
        val passwordString = password.text.trim().toString()
        presenter.login(userNameString,passwordString)
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