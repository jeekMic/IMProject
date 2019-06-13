package app.bxvip.com.mykotlin.presenter

import app.bxvip.com.mykotlin.contract.AddFriendContract
import app.bxvip.com.mykotlin.data.AddFriendItem
import app.bxvip.com.mykotlin.data.db.IMDatabase

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.a.e
import org.jetbrains.anko.doAsync
import java.nio.file.Files.size



class AddFriendPresenter(val view:AddFriendContract.View):AddFriendContract.Presenter {
    val addFriendItems = mutableListOf<AddFriendItem>()
    override fun search(key: String) {
        val categoryBmobQuery = BmobQuery<BmobUser>()
        categoryBmobQuery.addWhereEqualTo("username", key)
            .addWhereNotEqualTo("username",EMClient.getInstance().currentUser)
        categoryBmobQuery.findObjects(object : FindListener<BmobUser>() {
            override fun done(p0: MutableList<BmobUser>, e: BmobException?) {
                if (e == null) {
                    println("========查询成功")
                    //处理数据，创建AddFriendItem
                    //创建AddFriendItem的集合
                    val allContacts = IMDatabase.instance.getAllContact()
                    doAsync {
                        p0?.forEach {
                            println("========${it.username}===${it.createdAt}")
                            //比对是否已经添加过好友
                            var isAdded = false
                            for (contact in allContacts){
                                if (contact.name == it.username){
                                    isAdded = true
                                }
                            }
                            val addFriendItem = AddFriendItem(it.username, it.createdAt,isAdded)
                            addFriendItems.add(addFriendItem)
                        }
                        uiThread {
                            view.onSearchSuccess()
                        }
                    }

                } else {
                    println("========查询失败")
                    view.onSearchFaild()
                }
            }
        })
    }
}