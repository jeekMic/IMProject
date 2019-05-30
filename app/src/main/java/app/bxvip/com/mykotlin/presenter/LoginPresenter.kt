package app.bxvip.com.mykotlin.presenter

import app.bxvip.com.mykotlin.contract.LoginContract
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import extentions.isValidPassword
import extentions.isValidUserName

class LoginPresenter(val view:LoginContract.View) :LoginContract.presenter{
    override fun login(userName: String, password: String) {
            if(userName.isValidUserName()){
                //用户名合法
                if (password.isValidPassword()){
                    //密码合法 开始登陆
                    view.onStartLogin()
                    loginEaseMob(userName,password)//登陆
                }
            }else{

            }
    }

    private fun loginEaseMob(userName: String, password: String) {
        EMClient.getInstance().login(userName,password,object :EMCallBack{
            override fun onSuccess() {
                view.onLoginSuccess()
            }

            override fun onProgress(p0: Int, p1: String?) {

            }

            override fun onError(p0: Int, p1: String?) {
                view.onLoginFiald()
            }

        })
    }
}