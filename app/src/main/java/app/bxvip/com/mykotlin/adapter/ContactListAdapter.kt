package app.bxvip.com.mykotlin.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import app.bxvip.com.mykotlin.R
import app.bxvip.com.mykotlin.data.ContactListItem
import app.bxvip.com.mykotlin.ui.activity.ChatActivity
import app.bxvip.com.mykotlin.widget.ContactListItemView
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ContactListAdapter(
    val context: Context, val contactListItems: MutableList<ContactListItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            return ContactListernerViewHolder(ContactListItemView(context))
    }

    override fun getItemCount(): Int  = contactListItems.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val contactListItemView = p0.itemView as ContactListItemView
        contactListItemView.bindView(contactListItems[p1])
        val userName = contactListItems.get(p1).username
        contactListItemView.setOnClickListener { context.startActivity<ChatActivity>("userName" to userName) }
        contactListItemView.setOnLongClickListener{
            val message = String.format(context.getString(R.string.delete_friend_title), userName)
            AlertDialog.Builder(context).setTitle(R.string.delete_friend_title)
                .setMessage(message)
                .setNegativeButton(R.string.cancel,null)
                .setPositiveButton(R.string.confirm, DialogInterface.OnClickListener{
                    dialog, which ->
                    deleteFriend(userName)
                }).show()
            true
        }
    }

    private fun deleteFriend(userName: String) {
        EMClient.getInstance().contactManager().aysncDeleteContact(userName,object :EMCallBackAdapter(){
            override fun onSuccess() {
                context.runOnUiThread { toast(R.string.delete_friend_success) }

            }

            override fun onError(p0: Int, p1: String?) {
                context.runOnUiThread { toast(R.string.delete_friend_failed) }
            }
        })
    }

    class ContactListernerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}