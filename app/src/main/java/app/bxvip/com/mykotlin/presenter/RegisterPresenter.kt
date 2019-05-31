package app.bxvip.com.mykotlin.presenter

import app.bxvip.com.mykotlin.contract.RegisterContact
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import extentions.isValidPassword
import extentions.isValidUserName
import org.jetbrains.anko.doAsync

class RegisterPresenter(val view:RegisterContact.View) : RegisterContact.Presenter{
    override fun register(username: String, password: String, confirmPassword: String) {
        if(username.isValidUserName()){
            //用户名正确，检查密码
            if (password.isValidPassword()){
                //密码正确，检查确认密码是否正确
                if(password.equals(confirmPassword)){
                    //密码和确认密码正确,通知注册
                    //注册Bmod
                    registerBmod(username,password)
                }else{
                    view.onConfirmPasswordError()
                }
            }else{
                view.onPasswordError()
            }
        }else{
            view.onUserNameError()
        }
    }

    private fun registerBmod(username: String, password: String) {
        val bu = BmobUser()
        bu.username = username
        bu.setPassword(password)
        bu.mobilePhoneNumber = "13476827174"
        bu.email = "1915224525@qq.com"
        //是不能用save来注册的
        bu.signUp<BmobUser>(object : SaveListener<BmobUser>(){
            override fun done(p0: BmobUser?, p1: BmobException?) {
                if (p1 == null){
                    //注册成功
                    //注册到环信
                    registerEaseMob(username,password)
                }else{
                    //注册失败
                    view.onRegisterFialed()
                    registerEaseMob(username,password)
                }
            }

        })
    }

    private fun registerEaseMob(username: String, password: String) {

        //注册失败会抛出HyphenateException  这里要在子线程来做
        doAsync {
                try {
                    EMClient.getInstance().createAccount(username, password)//同步方法
                    //注册成功
                    uiThread {
                        view.onRegisterSuccess()
                    }
                }catch (e: HyphenateException){
                    //注册失败
                    uiThread {
                        view.onRegisterFialed()
                    }
                }
             }

    }
}