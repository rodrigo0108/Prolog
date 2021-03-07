package com.heyoh.prolog.util.extensions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker

fun Context.checkPermission(permissionName: String): Boolean {
    return if (Build.VERSION.SDK_INT >= 23) {
        val granted =
            ContextCompat.checkSelfPermission(this, permissionName)
        granted == PackageManager.PERMISSION_GRANTED
    } else {
        val granted =
            PermissionChecker.checkSelfPermission(this, permissionName)
        granted == PermissionChecker.PERMISSION_GRANTED
    }
}
