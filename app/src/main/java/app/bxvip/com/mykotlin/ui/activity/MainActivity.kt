package app.bxvip.com.mykotlin.ui.activity

import android.support.v4.app.Fragment
import app.bxvip.com.mykotlin.R
import app.bxvip.com.mykotlin.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        super.init()

        bottomBar.setOnTabSelectListener {
            tabId ->
            val beginTransaction = supportFragmentManager.beginTransaction()
            FragmentFactory.instance.getFragment(tabId)?.let { beginTransaction.replace(R.id.fragment_frame, it)
                beginTransaction.commit()
            }
        }

    }

}
