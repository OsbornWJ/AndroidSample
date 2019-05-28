package com.jeven.sample.utils.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
class SQLiteHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        createAllTable(db, false)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        dropAllTable(db, true)
        createAllTable(db, false)
    }

    private fun createAllTable(db: SQLiteDatabase, ifExists: Boolean) {
        UserTable.createTable(db, ifExists)
    }

    private fun dropAllTable(db: SQLiteDatabase, ifExists: Boolean) {
        UserTable.dropTable(db, ifExists)
    }

    companion object {
        private const val DATABASE_NAME = "sample_db"
        private const val DATABASE_VERSION = 1
    }
}
