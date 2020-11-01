package com.curtisy.oppounlocker.heytap.usercenter

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import android.text.TextUtils
import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.heytap.utils.SystemPropertyUtils
import com.curtisy.oppounlocker.utilities.XORUtils
import java.net.URLEncoder


object UCDeviceInfoUtil {
    /* renamed from: a */
    fun m6308a(context: Context?): String? {
        return if (context != null) {
            try {
                ClientIdUtils.m6327a(context)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else {
            throw NullPointerException("context is null.")
        }
    }

    /* renamed from: b */
    fun m6311b(context: Context): String {
        return try {
            val telephonyManager = context.getSystemService("phone") as TelephonyManager
            if (!TextUtils.isEmpty(telephonyManager.line1Number)) m6309a(telephonyManager.networkOperatorName) else BuildConfig.FLAVOR
        } catch (unused: Exception) {
            BuildConfig.FLAVOR
        }
    }

    /* renamed from: a */
    fun m6307a(): String {
        return try {
            m6309a(Build.VERSION.RELEASE)
        } catch (unused: Exception) {
            BuildConfig.FLAVOR
        }
    }

    /* renamed from: b */
    fun m6310b(): String {
        return try {
            m6309a(Build.MANUFACTURER)
        } catch (unused: Exception) {
            BuildConfig.FLAVOR
        }
    }

    /* renamed from: a */
    private fun m6309a(str: String): String {
        var str = str
        if (TextUtils.isEmpty(str)) {
            return BuildConfig.FLAVOR
        }
        if (str.length > 15) {
            str = str.substring(0, 15)
        }
        return try {
            URLEncoder.encode(str, "UTF-8")
        } catch (e: Exception) {
            e.printStackTrace()
            BuildConfig.FLAVOR
        }
    }

    /* renamed from: c */
    fun m6312c(): Boolean {
        val a = XORUtils.hash("zmidem")
        return a.equals(Build.BRAND, ignoreCase = true) || a.equals(SystemPropertyUtils.getProp("ro.product.brand.sub", XORUtils.hash("gxxg")), ignoreCase = true)
    }
}