package extentions

import cn.bmob.v3.util.V

//kotlin中可以对已存在的类进行自定义属性，也就是扩展性属性
fun String.isValidUserName():Boolean = this.matches(Regex("[a-zA-Z]\\w{2,19}"))
fun String.isValidPassword():Boolean = this.matches(Regex("[0-9]{3,20}"))
//同样 下面是MutableMap的扩展方法
fun<K,V> MutableMap<K, V>.toVararArray():Array<Pair<K,V>>{
    //将 MutableMap转化为Pair类型的数组
    return this.map{
        Pair(it.key, it.value)
    }.toTypedArray()
}