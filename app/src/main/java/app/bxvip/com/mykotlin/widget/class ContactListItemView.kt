package app.bxvip.com.mykotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import app.bxvip.com.mykotlin.R
import app.bxvip.com.mykotlin.data.ContactListItem
import kotlinx.android.synthetic.main.view_contact_item.view.*

// 表示默认的值为null AttributeSet?=null
class ContactListItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {

    //初始化代码块
    init {
        View.inflate(context, R.layout.view_contact_item,this)
    }
    fun bindView(contactListItem: ContactListItem) {
        println("===========")
        println(contactListItem.showFirstLetter)
       if (contactListItem.showFirstLetter){
           firstLetter.text = contactListItem.firstLetter.toString()
           userName.text = contactListItem.username
           firstLetter.visibility = View.VISIBLE
       }else{
           firstLetter.visibility = GONE
           userName.text = contactListItem.username
       }
    }


}