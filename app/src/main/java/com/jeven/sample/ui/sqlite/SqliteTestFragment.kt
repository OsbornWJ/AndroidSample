package com.jeven.sample.ui.sqlite

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeven.sample.R
import com.jeven.sample.ui.BaseFragment
import com.jeven.sample.ui.adapter.BaseAdapter
import com.jeven.sample.ui.adapter.BaseViewHolder
import com.jeven.sample.ui.sqlite.entity.User
import com.jeven.sample.utils.ToastUtils
import com.jeven.sample.utils.ViewClickFast
import com.jeven.sample.utils.sqlite.SQLiteHelper
import kotlinx.android.synthetic.main.comm_recyclerview.*
import kotlinx.android.synthetic.main.fragment_sqlite_test.*

/**
 * 创建人: Jeven
 * 功能:   数据库测试
 */
class SqliteTestFragment : BaseFragment() {

    var mData: MutableList<User> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sqlite_test, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewData.layoutManager = LinearLayoutManager(context)
        viewData.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        viewData.adapter = object : BaseAdapter<User>(mData, android.R.layout.simple_list_item_1) {
            override fun binfMultiViewHolder(viewHolder: BaseViewHolder, item: User) {
                viewHolder.setText(android.R.id.text1, item.id.toString() + "  " + item.name)
                    .setBackgroundColor(android.R.id.text1, 0xFFFAF0E6.toInt())
            }
        }

        addBtnAction.setOnClickListener (object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (ViewClickFast.isFastClick()) return
                if (TextUtils.isEmpty(etUser.text)) {
                    ToastUtils.showToast("输入用户名")
                    return
                } else if (TextUtils.isEmpty(etPassword.text)) {
                    ToastUtils.showToast("输入密码")
                    return
                }
                if (!SQLiteHelper.INSTANCE.daoSession!!.userTable.queryUserName(etUser.text.toString())) {
                    SQLiteHelper.INSTANCE.daoSession!!.userTable.insert(etUser.text.toString(), etPassword.text.toString())
                    getSqlData()
                } else ToastUtils.showToast("已有该用户")
            }
        })
        getSqlData()
    }

    private fun getSqlData() {
        mData.clear()
        val list = SQLiteHelper.INSTANCE.daoSession!!.userTable.queryAll()
        mData.addAll(list)
        viewData.adapter!!.notifyDataSetChanged()
    }

}
