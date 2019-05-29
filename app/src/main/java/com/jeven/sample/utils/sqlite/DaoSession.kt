package com.jeven.sample.utils.sqlite

import android.database.sqlite.SQLiteDatabase

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
class DaoSession(var writeDatabase: SQLiteDatabase) {

    var userTable: UserTable = UserTable(writeDatabase)



}