package app.bxvip.com.mykotlin.presenter

import app.bxvip.com.mykotlin.adapter.EMCallBackAdapter
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
        EMClient.getInstance().login(userName,password,object : EMCallBackAdapter() {
            //在子线程中回调的
            override fun onSuccess() {
                super.onSuccess()
                EMClient.getInstance().groupManager().loadAllGroups()
                EMClient.getInstance().chatManager().loadAllConversations()
                //在子线程中通知UI线程
                uiThread {  view.onLoginSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                uiThread {  view.onLoginFiald() }
            }

        })
    }
}