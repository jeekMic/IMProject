package app.bxvip.com.mykotlin.presenter

import app.bxvip.com.mykotlin.contract.ContactContract
import app.bxvip.com.mykotlin.data.ContactListItem
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.collections.forEachByIndex
import org.jetbrains.anko.doAsync


class ContactPresenter(var view: ContactContract.View): ContactContract.Presenter{
    val contactListItems = mutableListOf<ContactListItem>()
    override fun loadConracts() {
        println(contactListItems.size)
        contactListItems.clear()
        println(contactListItems.size)
            doAsync {
               try {
                   val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                   //根据首字符排序
                   usernames.sortBy { it[0] }
                   usernames.forEachIndexed { index,s ->
                       //判断是否显示首字符
                       val showFirstLetter =  (index == 0 || s[0]!=usernames[index-1][0])
                       println(showFirstLetter)
                       //创建一个对象，将上面的数据赋值给对象
                       val contactListItem = ContactListItem(s,s[0].toUpperCase(),showFirstLetter)
                       contactListItems.add(contactListItem)
                   }

                    uiThread { view.onLoadContactSuccess() }
               }catch (e: HyphenateException){
                   uiThread { view.onLoadContactFaild() }
               }
            }
    }
}