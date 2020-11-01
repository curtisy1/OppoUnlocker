package com.curtisy.oppounlocker.heytap.utils

import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import com.curtisy.oppounlocker.BuildConfig


object ApkInfoUtil {
    private const val OPPO_MODEL = "ro.product.model"
    private const val OPPO_OS_VERSION = "ro.build_bak.version.opporom"
    private const val OPPO_ROM_VERSION = "ro.build_bak.display.id"
    const val OPPO_VERSION_CN = "CN"
    const val OPPO_VERSION_EXPORT_PROPERTY = "ro.oppo.version"
    const val OPPO_VERSION_PROPERTY = "persist.sys.oppo.region"
    const val SSOID = "ssoid"
    private const val SSOID_DEFAULT = "0"
    var sRegionCode = regionCode
    val regionCode: String
        get() {
            val str: String = SystemProperties.get(OPPO_VERSION_EXPORT_PROPERTY, OPPO_VERSION_CN)
            LogUtil.m6340d("getRegionCode:$str")
            return str
        }
    val isRegionCN: Boolean
        get() = OPPO_VERSION_CN == sRegionCode
    val androidOSVersion: Int
        get() = Build.VERSION.SDK_INT

    fun getVersionName(context: Context): String {
        return try {
            context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName
        } catch (e: Exception) {
            e.printStackTrace()
            SSOID_DEFAULT
        }
    }

    val romVersion: String
        get() = SystemProperties.get(OPPO_ROM_VERSION, "")

    fun getVersionCode(context: Context): Int {
        return try {
            context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode
        } catch (unused: Exception) {
            LogUtil.m6343e("getVersionCode--Exception")
            0
        }
    }

    fun getPackageName(context: Context): String {
        return try {
            context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName
        } catch (e: Exception) {
            LogUtil.m6343e("getPackageName:$e")
            SSOID_DEFAULT
        }
    }

    fun getAppCode(context: Context): Int {
        return try {
            context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getInt("AppCode")
        } catch (e: Exception) {
            LogUtil.m6343e("getPackageName:$e")
            0
        }
    }

    val model: String
        get() = SystemProperties.get(OPPO_MODEL, "")
    val osVersion: String
        get() = SystemProperties.get(OPPO_OS_VERSION, "V1.0.0")

    fun getSettingPref(context: Context?): SharedPreferences? {
        return if (context == null) {
            null
        } else context.getSharedPreferences("nearme_setting_" + getPackageName(context), 0)
    }

    fun getSsoID(context: Context?): String? {
        var str = SSOID_DEFAULT
        val settingPref = getSettingPref(context)
        if (settingPref != null) {
            str = settingPref.getString(SSOID, SSOID_DEFAULT)
        }
        if (str == SSOID_DEFAULT) {
            LogUtil.m6343e("getSsoID not set.")
        }
        return str
    }

    fun isExistPackage(context: Context, str: String?): Boolean {
        return try {
            context.getPackageManager().getPackageInfo(str, 1)
            true
        } catch (unused: PackageManager.NameNotFoundException) {
            LogUtil.m6343e("isExistPackage NameNotFoundException")
            false
        }
    }
}