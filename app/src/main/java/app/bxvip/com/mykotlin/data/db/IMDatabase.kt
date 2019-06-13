package app.bxvip.com.mykotlin.data.db

import extentions.toVararArray
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.dropTable
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class IMDatabase {
    companion object {
        val databaseHelper = DatabaseHelper()
        val instance = IMDatabase()
    }

    fun saveContact(contact: Contact) {
        databaseHelper.use {
            //SQL的扩展方法 *表示展开
            insert(ContactTable.NAME, *contact.map.toVararArray())
        }
    }


    fun getAllContact():List<Contact> = databaseHelper.use {
         select(ContactTable.NAME).parseList(object :MapRowParser<Contact>{
             override fun parseRow(columns: Map<String, Any?>): Contact {
                 return Contact(columns.toMutableMap())
             }
         })
    }

    fun deleteAllContacts(){
        databaseHelper.use{
            dropTable("User", true)
            delete(ContactTable.NAME,null,null)
        }

    }

}