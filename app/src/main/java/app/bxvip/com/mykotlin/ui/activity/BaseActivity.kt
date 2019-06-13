package app.bxvip.com.mykotlin.ui.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

abstract  class BaseActivity : AppCompatActivity(){

    val progressDialog by lazy {
        ProgressDialog(this)
    }
    val inputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
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

    fun hideSoftKeyBord(){
        inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken,0)
    }
}