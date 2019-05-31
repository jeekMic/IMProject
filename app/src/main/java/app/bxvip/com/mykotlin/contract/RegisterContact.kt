package app.bxvip.com.mykotlin.contract

interface RegisterContact {
    interface Presenter:BasePresenter{
        fun register(username:String, password:String, confirmPassword:String)
    }
    interface View{
        fun onUserNameError()
        fun onPasswordError()
        fun onConfirmPasswordError()
        fun onStartRegister()
        fun onRegisterSuccess()
        fun onRegisterFialed()
    }
}