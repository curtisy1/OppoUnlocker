package com.curtisy.oppounlocker.heytap.helper

import android.text.TextUtils
import android.util.Base64
import android.util.Log
import com.curtisy.oppounlocker.BuildConfig
import java.nio.charset.Charset


object Base64Helper {
    const val TAG = "USERCENTERSDK"
    fun base64Decode(str: String?): String {
        return try {
            if (TextUtils.isEmpty(str)) {
                BuildConfig.FLAVOR
            } else String(Base64.decode(str, 0), Charset.forName("UTF-8"))
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            BuildConfig.FLAVOR
        }
    }

    fun base64Encode(str: String): String {
        return try {
            if (TextUtils.isEmpty(str)) {
                BuildConfig.FLAVOR
            } else String(Base64.encode(str.toByteArray(), 0), Charset.forName("UTF-8"))
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            BuildConfig.FLAVOR
        }
    }
}
