package app.bxvip.com.mykotlin.contract

interface LoginContract {


    interface presenter:BasePresenter{
        fun  login(userName:String, password:String)
    }

    interface View{
        fun pnUserNameError()
        fun onPasswordError()
        fun onStartLogin()
        fun onLoginSuccess()
        fun onLoginFiald()
    }
}