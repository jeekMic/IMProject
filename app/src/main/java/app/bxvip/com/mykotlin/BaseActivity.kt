package app.bxvip.com.mykotlin

import android.app.ProgressDialog
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import org.jetbrains.anko.progressDialog

abstract  class BaseActivity : AppCompatActivity(){
    val progressDialog by lazy {
        ProgressDialog(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()

    }
    //子类必须实现这个方法返回一个布局ID
    abstract fun getLayoutId():Int

    open fun init(){
     //初始化一些公共的功能，子类也可以复写该方法
    }

    fun showPregress(message:String){
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    fun dismissProgress(){
        progressDialog.dismiss()
    }
}