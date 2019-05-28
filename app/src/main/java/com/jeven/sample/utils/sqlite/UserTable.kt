package com.jeven.sample.utils.sqlite

import android.database.sqlite.SQLiteDatabase

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
class UserTable : AbstractTable() {

    companion object {
        private const val TABLE_NAME = "userTable"

        fun createTable(dataBase: SQLiteDatabase, ifExists: Boolean) {
            val sql = "create table" + (if (ifExists) "IF EXISTS " else "") +
                    "$TABLE_NAME(Id Integer primary key autoincrement, UserName text, Account text, Password text)"
            dataBase.execSQL(sql)
        }

        fun dropTable(dataBase: SQLiteDatabase, ifExists: Boolean) {
            val sql = "DROP TABLE " + (if (ifExists) "IF EXISTS " else "") + TABLE_NAME
            dataBase.execSQL(sql)
        }
    }

}