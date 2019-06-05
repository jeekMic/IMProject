package app.bxvip.com.mykotlin.ui.fragment


import android.content.Intent
import app.bxvip.com.mykotlin.R
import app.bxvip.com.mykotlin.adapter.EMCallBackAdapter
import app.bxvip.com.mykotlin.ui.activity.LoginActivity
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.custom.onUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class DynamicFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_dynamic
    override fun init() {
        super.init()

        headerTitle.text = getString(R.string.dynamic)
        val logoutString = String.format(getString(R.string.logout, EMClient.getInstance().currentUser))
        logout.text =logoutString
        logout.setOnClickListener { logoutUser() }
    }

    private fun logoutUser() {
        EMClient.getInstance().logout(true, object : EMCallBackAdapter() {
            override fun onSuccess() {
                context?.onUiThread { toast(R.string.logout_success) }
                context?.startActivity(Intent(context,LoginActivity::class.java))
                activity?.finish()
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                context?.onUiThread { toast(R.string.logout_failed) }
            }

        })
    }
}
