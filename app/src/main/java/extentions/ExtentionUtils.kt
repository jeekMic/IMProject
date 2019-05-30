package extentions
//kotlin中可以对已存在的类进行自定义属性，也就是扩展性属性
fun String.isValidUserName():Boolean = this.matches(Regex("[a-zA-Z]\\w{2,19}"))
fun String.isValidPassword():Boolean = this.matches(Regex("[0-9]{3,20}"))