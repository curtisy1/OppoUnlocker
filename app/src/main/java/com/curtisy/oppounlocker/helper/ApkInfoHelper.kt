package com.curtisy.oppounlocker.helper

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager


object ApkInfoHelper {
    /* renamed from: a */
    fun m6317a(context: Context): Int {
        return try {
            context.packageManager.getPackageInfo(context.packageName, 0).versionCode
        } catch (e: Exception) {
            0
        }
    }

    /* renamed from: a */
    fun m6318a(context: Context, str: String): Int {
        return try {
            context.packageManager.getPackageInfo(str, 0).versionCode
        } catch (e: Exception) {
            0
        }
    }

    /* renamed from: b */
    fun m6319b(context: Context, str: String): Boolean {
        return try {
            context.packageManager.getPackageInfo(str, PackageManager.GET_ACTIVITIES)
            true
        } catch (unused: Exception) {
            false
        }
    }

    /* renamed from: c */
    fun m6320c(context: Context?, str: String): Boolean {
        return try {
            val packageInfo = context?.packageManager?.getPackageInfo(str, PackageManager.GET_UNINSTALLED_PACKAGES)
            !(packageInfo?.applicationInfo == null || !packageInfo.applicationInfo.enabled)
        } catch (unused: Exception) {
            false
        }
    }
}