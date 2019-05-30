package app.bxvip.com.mykotlin.presenter

import app.bxvip.com.mykotlin.contract.SplashContract
import com.hyphenate.chat.EMClient

class SplashPresenter(val view:SplashContract.View): SplashContract.Presenter {
    override fun checkLoginState() {
        if(isLogIn()) view.onLoginIn() else view.onNotLogin()
    }
    //是否已经登陆到环信的服务器
    private  fun isLogIn():Boolean =  EMClient.getInstance().isConnected && EMClient.getInstance().isLoggedInBefore
}