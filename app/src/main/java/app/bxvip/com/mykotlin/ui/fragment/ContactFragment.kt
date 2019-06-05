package app.bxvip.com.mykotlin.ui.fragment


import android.support.v7.widget.LinearLayoutManager
import android.view.View
import app.bxvip.com.mykotlin.R
import app.bxvip.com.mykotlin.adapter.ContactListAdapter
import app.bxvip.com.mykotlin.contract.ContactContract
import app.bxvip.com.mykotlin.presenter.ContactPresenter
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class ContactFragment : BaseFragment() , ContactContract.View{
    var presenter = ContactPresenter(this)
    override fun onLoadContactSuccess() {
        //加载成功后需要将加载的进度条给隐藏了
        swipeRefreshLayout.isRefreshing = false
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onLoadContactFaild() {
        swipeRefreshLayout.isRefreshing = false
        //弹出toast告诉用户加载数据失败
        context?.toast(R.string.load_contacts_failed)
    }

    override fun getLayoutId(): Int = R.layout.fragment_contacts
    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
            setOnRefreshListener {
                presenter.loadConracts()
            }
        }
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context,presenter.contactListItems)
        }
        //初始化的时候加载数据
        presenter.loadConracts()
    }
}
