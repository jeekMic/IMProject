package app.bxvip.com.mykotlin.factory

import android.support.v4.app.Fragment
import app.bxvip.com.mykotlin.R
import app.bxvip.com.mykotlin.ui.fragment.ContactFragment
import app.bxvip.com.mykotlin.ui.fragment.ConversationFragment
import app.bxvip.com.mykotlin.ui.fragment.DynamicFragment

//private constructor() 表示私有化构造方法 单例模式
class FragmentFactory private constructor(){
    val conversations by lazy { ConversationFragment() }
    val contactFragment by lazy { ContactFragment() }
    val dynamicFragment by lazy { DynamicFragment() }
    companion object{
        val instance = FragmentFactory()
    }

    fun getFragment(tabId: Int):Fragment?{
        when(tabId){
            R.id.tab_conversation -> return conversations
            R.id.tab_contacts -> return contactFragment
            R.id.tab_dynamic -> return dynamicFragment
        }
        return null
    }
}