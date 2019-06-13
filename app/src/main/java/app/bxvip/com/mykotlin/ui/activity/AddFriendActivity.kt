package app.bxvip.com.mykotlin.ui.activity

import android.support.v7.widget.LinearLayoutManager
import app.bxvip.com.mykotlin.R
import app.bxvip.com.mykotlin.adapter.AddFriendListAdapter
import app.bxvip.com.mykotlin.contract.AddFriendContract
import app.bxvip.com.mykotlin.presenter.AddFriendPresenter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.activity_add_friend.view.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class AddFriendActivity: BaseActivity(), AddFriendContract.View {
    val presenter = AddFriendPresenter(this)

    override fun getLayoutId(): Int {
        return R.layout.activity_add_friend
    }

    override fun init() {
        headerTitle.text = getString(R.string.add_friend)
        recyclerView.apply {
            //固定大小
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter =AddFriendListAdapter(context, presenter.addFriendItems)
        }
        search.setOnClickListener {
            searchFriend()
        }
        userName.setOnEditorActionListener { v, actionId, event ->
            searchFriend()
            true
        }
    }
    fun searchFriend(){
        hideSoftKeyBord()
        showPregress(getString(R.string.searching))
        val key = userName.text.trim().toString()
        presenter.search(key)
    }

    override fun onSearchSuccess() {
        dismissProgress()
        toast(R.string.search_success)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onSearchFaild() {
        dismissProgress()
        toast(R.string.search_failed)

    }
}