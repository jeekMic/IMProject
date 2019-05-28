package app.bxvip.com.mykotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

abstract  class BaseActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }
    //子类必须实现这个方法返回一个布局ID
    abstract fun getLayoutId():Int

    open fun init(){
        //初始化一些公共的功能，子类也可以复写该方法
    }
}