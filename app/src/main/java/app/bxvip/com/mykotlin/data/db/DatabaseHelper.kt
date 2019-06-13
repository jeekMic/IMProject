package app.bxvip.com.mykotlin.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import app.bxvip.com.mykotlin.app.IMApplication
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context = IMApplication.instant) :
    ManagedSQLiteOpenHelper(ctx, NAME, null, VERSION) {

    companion object{
        val NAME = "im.db"
        val VERSION = 1
    }
    //数据库的创建
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(ContactTable.NAME, true,
            ContactTable.ID to INTEGER+ PRIMARY_KEY+ AUTOINCREMENT,
            ContactTable.CONTACT to TEXT)
    }
    //数据库的升级
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(ContactTable.NAME, true)
        onCreate(db)
    }

}