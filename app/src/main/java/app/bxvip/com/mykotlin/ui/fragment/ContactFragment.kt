package app.bxvip.com.mykotlin.ui.fragment


import android.support.v7.widget.LinearLayoutManager
import android.view.View
import app.bxvip.com.mykotlin.R
import app.bxvip.com.mykotlin.adapter.ContactListAdapter
import app.bxvip.com.mykotlin.adapter.EMContactListenerAdapter
import app.bxvip.com.mykotlin.contract.ContactContract
import app.bxvip.com.mykotlin.presenter.ContactPresenter
import app.bxvip.com.mykotlin.ui.activity.AddFriendActivity
import app.bxvip.com.mykotlin.widget.SliderBar
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
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
        add.setOnClickListener {
                context?.startActivity<AddFriendActivity>()
        }
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
        EMClient.getInstance().contactManager().setContactListener(object: EMContactListenerAdapter(){
            override fun onContactDeleted(p0: String?) {
                //重新获取联系人数据
                presenter.loadConracts()
            }

            override fun onContactAdded(p0: String?) {
                //重新获取联系人的数据，这里其实是一个增加好友后的回调信息
            }
        })
        slideBar.onSectionChangeListener = object : SliderBar.OnSectionChangeListener {
            override fun onSliderFinish() {
                //手指抬起，滑动结束
                section.visibility = View.GONE
            }

            override fun onSectionChange(firstLetter: String) {
                section.visibility = View.VISIBLE
                section.text = firstLetter
                recyclerView.smoothScrollToPosition(getPosition(firstLetter))
            }

        }

        //初始化的时候加载数据
        presenter.loadConracts()
    }

    private fun getPosition(firstLetter: String): Int {
        return presenter.contactListItems.binarySearch { contactListItem ->  contactListItem.firstLetter.minus(firstLetter[0])        }
    }
}
