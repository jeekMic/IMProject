package app.bxvip.com.mykotlin.contract

import android.os.Handler
import android.os.Looper


interface BasePresenter {
    companion object{
        val handler by lazy {
            //如果我们使用的是空的构造函数，这绑定的是当前的线程，这里使用Looper.getMainLooper()参数的构造函数使得这个Handler是主线程中的
            Handler(Looper.getMainLooper())
        }
    }
    //BasePresenter这里在BasePresenter中定义后， 在后面的子类中可以随意的切换线程进行UI的操作
    fun uiThread(f: ()->Unit){
        handler.post { f() }
    }

}