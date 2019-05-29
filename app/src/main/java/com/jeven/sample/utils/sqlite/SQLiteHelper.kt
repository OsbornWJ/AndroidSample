package com.jeven.sample.utils.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.jeven.sample.utils.AppUtil

/**
 * 创建人: Jeven
 * 功能:
 */
class SQLiteHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    var daoSession: DaoSession? = null

    init {
        daoSession = DaoSession(this.writableDatabase)
    }

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

        val INSTANCE: SQLiteHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SQLiteHelper(AppUtil.getApplication())
        }
    }
}
