package app.bxvip.com.mykotlin.contract

interface ContactContract {

    interface Presenter:BasePresenter{
        fun loadConracts() //加载联系人数据
    }

    interface View{
        fun onLoadContactSuccess()
        fun onLoadContactFaild()
    }
}