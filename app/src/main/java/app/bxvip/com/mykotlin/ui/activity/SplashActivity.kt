package app.bxvip.com.mykotlin.ui.activity

import android.os.Handler
import app.bxvip.com.mykotlin.R
import app.bxvip.com.mykotlin.contract.SplashContract
import app.bxvip.com.mykotlin.presenter.SplashPresenter
import org.jetbrains.anko.startActivity


class SplashActivity: BaseActivity(), SplashContract.View {
    val presenter = SplashPresenter(this)
    companion object{
        val DELAY:Long = 2000L
    }
    val handler by lazy{
        Handler()
    }
    //延时2秒跳转到登录界面
    override fun onNotLogin() {
        handler.postDelayed({
                startActivity<LoginActivity>()
                finish()

        }, DELAY)
    }

    override fun init() {
        super.init()
        println("hb===init")
        presenter.checkLoginState()
    }
    //延时2秒跳转到登录界面
    override fun onLoginIn() {
        startActivity<MainActivity>()
        finish()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }
}