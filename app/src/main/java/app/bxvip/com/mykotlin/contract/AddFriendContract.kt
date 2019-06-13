package app.bxvip.com.mykotlin.contract

interface AddFriendContract {
    interface Presenter:BasePresenter{
        fun search(key:String)
    }
    interface View{
        fun  onSearchSuccess()
        fun onSearchFaild()
    }
}