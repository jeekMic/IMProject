package app.bxvip.com.mykotlin.contract

import android.support.v7.view.menu.BaseMenuPresenter

interface SplashContract {
    interface  Presenter: BasePresenter{
        fun  checkLoginState()//检查登录的状态
    }

    interface  View{
        fun onNotLogin()
        fun onLoginIn()
    }
}