package com.curtisy.oppounlocker.heytap.models

import android.text.TextUtils
import org.json.JSONException
import org.json.JSONObject


class AppInfo {
    var appCode: String? = null
    var appVersion = 0
    var packageName: String? = null
    var secreKey: String? = null

    companion object {
        fun fromGson(str: String?): AppInfo? {
            if (TextUtils.isEmpty(str)) {
                return null
            }
            val appInfo = AppInfo()
            return try {
                val jSONObject = JSONObject(str)
                if (!jSONObject.isNull("appCode") && jSONObject["appCode"] !== JSONObject.NULL) {
                    appInfo.appCode = jSONObject.getString("appCode")
                }
                if (!jSONObject.isNull("secreKey") && jSONObject["secreKey"] !== JSONObject.NULL) {
                    appInfo.secreKey = jSONObject.getString("secreKey")
                }
                if (!jSONObject.isNull("packageName") && jSONObject["packageName"] !== JSONObject.NULL) {
                    appInfo.packageName = jSONObject.getString("packageName")
                }
                if (!jSONObject.isNull("appVersion") && jSONObject["appVersion"] !== JSONObject.NULL) {
                    appInfo.packageName = jSONObject.getString("appVersion")
                }
                appInfo
            } catch (e: JSONException) {
                e.printStackTrace()
                null
            }
        }

        fun toJson(appInfo: AppInfo?): String? {
            return if (appInfo == null) {
                null
            } else try {
                val jSONObject = JSONObject()
                jSONObject.put("appCode", appInfo.appCode)
                jSONObject.put("secreKey", appInfo.secreKey)
                jSONObject.put("packageName", appInfo.packageName)
                jSONObject.put("appVersion", appInfo.appVersion)
                jSONObject.toString()
            } catch (e: JSONException) {
                e.printStackTrace()
                null
            }
        }
    }
}