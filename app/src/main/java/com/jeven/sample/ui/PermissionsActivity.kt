package com.jeven.sample.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.provider.Settings
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.jeven.sample.R
import com.jeven.sample.utils.AppUtil
import permissions.dispatcher.*

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:   权限申请
 */

@SuppressLint("Registered")
@Suppress("PropertyName", "MemberVisibilityCanBePrivate", "unused")
@RuntimePermissions
open class PermissionsActivity: AppCompatActivity() {

    /**
     * @RuntimePermissions Required
     * @NeedsPermission Required
     * @OnShowRationale
     * @OnPermissionDenied Annotate a method which is invoked if the user doesn't grant the permissions
     * @OnNeverAskAgain Annotate a method which is invoked if the user chose to have the device "never ask again" about a permission
     */

    protected val REQUEST_CODE_OPEN_GPS:Int = 2001

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun enableGPS(message: String) {
        if (!AppUtil.checkGPSIsOpen()) {
            AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage(message)
                .setNegativeButton("取消"
                ) { dialog, _ -> dialog!!.dismiss() }
                .setPositiveButton("确定") { dialog, _ ->
                    run {
                        dialog.dismiss()
                        startActivityForResult(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), REQUEST_CODE_OPEN_GPS)
                    }
                }
                .show()
        }
    }

    @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
    fun showRationaleForLocation(request: PermissionRequest) {
        showRationaleDialog(R.string.permission_location, request)
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun onLocationDenied() {

    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    fun onLocationNeverAskAgain() {

    }

    private fun showRationaleDialog(@StringRes messageResId: Int, request: PermissionRequest) {
        AlertDialog.Builder(this)
            .setPositiveButton(R.string.button_allow) { _, _ -> request.proceed() }
            .setNegativeButton(R.string.button_deny) { _, _ -> request.cancel() }
            .setCancelable(false)
            .setMessage(messageResId)
            .show()
    }

}