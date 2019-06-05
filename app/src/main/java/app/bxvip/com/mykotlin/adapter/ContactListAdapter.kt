package app.bxvip.com.mykotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import app.bxvip.com.mykotlin.data.ContactListItem
import app.bxvip.com.mykotlin.widget.ContactListItemView

class ContactListAdapter(
    val context: Context, val contactListItems: MutableList<ContactListItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            return ContactListernerViewHolder(ContactListItemView(context))
    }

    override fun getItemCount(): Int  = contactListItems.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val contactListItemView = p0.itemView as ContactListItemView
        contactListItemView.bindView(contactListItems[p1])
    }

    class ContactListernerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}