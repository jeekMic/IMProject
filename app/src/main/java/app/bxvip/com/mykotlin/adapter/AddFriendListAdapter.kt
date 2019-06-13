package app.bxvip.com.mykotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import app.bxvip.com.mykotlin.data.AddFriendItem
import app.bxvip.com.mykotlin.widget.AddFriendListItemView

class AddFriendListAdapter(val context:Context, val addFrienditem:MutableList<AddFriendItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
    return AddFriendItemViewHolder(AddFriendListItemView(context))
    }

    override fun getItemCount(): Int {
        return addFrienditem.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val addFriendListItemView = p0.itemView as AddFriendListItemView
        addFriendListItemView.bindView(addFrienditem[p1])
    }

    class AddFriendItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}

