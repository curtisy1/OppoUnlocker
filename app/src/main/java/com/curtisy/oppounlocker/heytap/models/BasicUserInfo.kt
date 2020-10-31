package com.curtisy.oppounlocker.heytap.models

import android.text.TextUtils
import com.curtisy.oppounlocker.heytap.utils.ApkInfoUtil
import com.curtisy.oppounlocker.heytap.usercenter.UCUtils
import org.json.JSONException
import org.json.JSONObject

class BasicUserInfo {
    var accountName: String? = null
    var avatarUrl: String? = null
    var boundEmail: String? = null
    var boundPhone: String? = null
    var country: String? = null
    var countryCallingCode: String? = null
    var ssoid: String? = null
    var status: String? = null
    var userName: String? = null
    var userNameNeedModify = false

    fun toJson(): String? {
        return try {
            val jSONObject = JSONObject()
            jSONObject.put("accountName", accountName)
            jSONObject.put("avatarUrl", avatarUrl)
            jSONObject.put("userName", userName)
            jSONObject.put("userNameNeedModify", userNameNeedModify)
            jSONObject.put("countryCallingCode", countryCallingCode)
            jSONObject.put("boundPhone", boundPhone)
            jSONObject.put("boundEmail", boundEmail)
            jSONObject.put("accountName", accountName)
            jSONObject.put("country", country)
            jSONObject.put(ApkInfoUtil.SSOID, ssoid)
            jSONObject.toString()
        } catch (e: JSONException) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        fun fromJson(str: String?): BasicUserInfo? {
            var e: JSONException
            var basicUserInfo: BasicUserInfo? = null
            return if (TextUtils.isEmpty(str)) {
                null
            } else try {
                val jSONObject = JSONObject(str)
                val basicUserInfo2 = BasicUserInfo()
                try {
                    basicUserInfo2.accountName = UCUtils.m6331a(jSONObject, "accountName")
                    basicUserInfo2.avatarUrl = UCUtils.m6331a(jSONObject, "avatarUrl")
                    basicUserInfo2.boundEmail = UCUtils.m6331a(jSONObject, "boundEmail")
                    basicUserInfo2.boundPhone = UCUtils.m6331a(jSONObject, "boundPhone")
                    basicUserInfo2.country = UCUtils.m6331a(jSONObject, "country")
                    basicUserInfo2.countryCallingCode = UCUtils.m6331a(jSONObject, "countryCallingCode")
                    basicUserInfo2.status = UCUtils.m6331a(jSONObject, "status")
                    basicUserInfo2.userName = UCUtils.m6331a(jSONObject, "userName")
                    basicUserInfo2.ssoid = UCUtils.m6331a(jSONObject, ApkInfoUtil.SSOID)
                    basicUserInfo2.userNameNeedModify = UCUtils.m6332b(jSONObject, "userNameNeedModify")
                    basicUserInfo2
                } catch (e2: JSONException) {
                    e = e2
                    basicUserInfo = basicUserInfo2
                    e.printStackTrace()
                    basicUserInfo
                }
            } catch (e3: JSONException) {
                e = e3
                e.printStackTrace()
                basicUserInfo
            }
        }
    }
}