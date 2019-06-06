package com.jeven.sample.utils.sqlite

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.jeven.sample.ui.sqlite.entity.User

/**
 * 创建人: Jeven
 * 功能:
 */
class UserTable(var dataBase: SQLiteDatabase) : AbstractTable() {

    companion object {
        private const val TABLE_NAME = "userTable"

        fun createTable(dataBase: SQLiteDatabase, ifExists: Boolean) {
            val sql = "create table " + (if (ifExists) "IF EXISTS " else "") +
                    "$TABLE_NAME(Id Integer primary key autoincrement, UserName text, Account text, Password text)"
            dataBase.execSQL(sql)
        }

        fun dropTable(dataBase: SQLiteDatabase, ifExists: Boolean) {
            val sql = "DROP TABLE " + (if (ifExists) "IF EXISTS " else "") + TABLE_NAME
            dataBase.execSQL(sql)
        }
    }

    fun insert(userName: String, passWord: String) {
        val contentValues = ContentValues()
        contentValues.put("UserName", userName)
        contentValues.put("Password", passWord)
        insert(contentValues)
    }

    fun insert(contentValues: ContentValues) {
        dataBase.insert(TABLE_NAME, null, contentValues)
    }

    fun queryUserName(userName: String): Boolean {
        val cursor = dataBase.query(TABLE_NAME, arrayOf("UserName"), "UserName = ?", arrayOf(userName), null, null, null)
        if (cursor.moveToNext()) {
            return true
        }
        cursor.close()
        return false
    }

    fun queryAll(): MutableList<User> {
        val users = mutableListOf<User>()
        val cursor = dataBase.query(TABLE_NAME, arrayOf("Id", "UserName", "Password"), null, null, null, null, null)
        while (cursor.moveToNext()) {
            users.add(User(cursor.getInt(cursor.getColumnIndex("Id")),
                cursor.getString(cursor.getColumnIndex("UserName")), cursor.getString(cursor.getColumnIndex("Password"))))
        }
        cursor.close()
        return users
    }

    fun getCursor(): Cursor {
        return dataBase.query(TABLE_NAME, arrayOf("Id", "UserName", "Password"), null, null, null, null, null)
    }

    fun deleteUser(userName: String) {
        dataBase.delete(TABLE_NAME, "UserName = ?", arrayOf(userName))
    }

    fun deleteUser(whereClause: String , whereArgs: Array<String>): Int {
        return dataBase.delete(TABLE_NAME, whereClause, whereArgs)
    }

    fun updateUser(user: User) {
        val contentValues = ContentValues()
        contentValues.put("Id", user.id)
        contentValues.put("UserName", user.name)
        contentValues.put("Password", user.password)
        updateUser(contentValues)
    }

    fun updateUser(contentValues: ContentValues): Int {
        return dataBase.update(TABLE_NAME, contentValues, "Id = ?", arrayOf(contentValues.get("Id").toString()))
    }

}