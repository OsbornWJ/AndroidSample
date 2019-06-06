package com.jeven.sample.ui.sqlite

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.jeven.sample.utils.sqlite.SQLiteHelper

/**
 * 创建人: Jeven
 * 邮箱:   Osbornjie@163.cn
 * 功能:
 */
class MyDataProvider : ContentProvider() {

    companion object {
        //数据改变后指定通知的Uri
        private const val AUTHORITIES = "com.layoutSample.provider"
        private val NOTIFY_URI = Uri.parse("content://$AUTHORITIES/user")
        //匹配码
        private const val MATCH_CODE = 1001

        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    }

    init {
        uriMatcher.addURI(AUTHORITIES, "user", MATCH_CODE)
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        if (uriMatcher.match(uri) == MATCH_CODE) {
            SQLiteHelper.INSTANCE.daoSession!!.userTable.insert(values!!)
        }
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        if (uriMatcher.match(uri) == MATCH_CODE) {
            return SQLiteHelper.INSTANCE.daoSession!!.userTable.getCursor()
        }
        return null
    }

    override fun onCreate(): Boolean {
        return false
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        if (uriMatcher.match(uri) == MATCH_CODE) {
            val modify: Int = SQLiteHelper.INSTANCE.daoSession!!.userTable.updateUser(values!!)
            notifyDataChanged()
            return modify
        }
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val modify: Int = SQLiteHelper.INSTANCE.daoSession!!.userTable.deleteUser(selection!!, selectionArgs!!)
        notifyDataChanged()
        return modify
    }

    override fun getType(uri: Uri): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun notifyDataChanged() {
        context.contentResolver.notifyChange(NOTIFY_URI, null)
    }

}