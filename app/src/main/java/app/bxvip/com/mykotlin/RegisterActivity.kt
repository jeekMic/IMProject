package app.bxvip.com.mykotlin

import android.view.KeyEvent
import android.widget.TextView
import app.bxvip.com.mykotlin.contract.RegisterContact
import app.bxvip.com.mykotlin.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity: BaseActivity() , RegisterContact.View{
    val presenter = RegisterPresenter(this)

    override fun init() {
        super.init()
        register.setOnClickListener {
            registerUser()
        }
        //输入了确认密码后，点击回车也触发注册事件
        confirmPassword.setOnEditorActionListener{
                _, _, _ ->
            registerUser()
            true
        }
    }
    fun registerUser(){
        val userNameString = userName.text.trim().toString()
        val passwordString = password.text.trim().toString()
        val passwordConfirmString = confirmPassword.text.trim().toString()
        presenter.register(userNameString,passwordString,passwordConfirmString)
    }
    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onConfirmPasswordError() {
        confirmPassword.error = getString(R.string.confirm_password_error)
    }

    override fun onStartRegister() {
        showPregress(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        dismissProgress()
        toast(R.string.register_success)
        //干掉自己
        finish()
    }

    override fun onRegisterFialed() {
        dismissProgress()
        toast(R.string.register_failed)
    }

    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }
}