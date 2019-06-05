package app.bxvip.com.mykotlin.presenter

import app.bxvip.com.mykotlin.contract.ContactContract
import app.bxvip.com.mykotlin.data.ContactListItem
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync


class ContactPresenter(var view: ContactContract.View): ContactContract.Presenter{
    val contactListItems = mutableListOf<ContactListItem>()
    override fun loadConracts() {

            doAsync {
               try {
                   val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                   //根据首字符排序
                   usernames.sortBy { it[0] }
                   usernames.forEach {
                       //创建一个对象，将上面的数据赋值给对象
                       val contactListItem = ContactListItem(it,it[0].toUpperCase())
                       contactListItems.add(contactListItem)
                   }
                    uiThread { view.onLoadContactSuccess() }
               }catch (e: HyphenateException){
                   uiThread { view.onLoadContactFaild() }
               }
            }
    }
}