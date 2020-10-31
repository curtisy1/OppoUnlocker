package com.curtisy.oppounlocker.heytap.account

import android.text.TextUtils
import org.json.JSONException
import org.json.JSONObject


class AccountResult {
    var accountName: String? = null
    var isCanJump2Bind = false
    var isNameModified = false
    var isNeedBind = false
    var oldUserName: String? = null
    var resultCode = 0
    var resultMsg: String? = null

    constructor() {}

    override fun toString(): String {
        return "resultCode = $resultCode , resultMsg = $resultMsg , isNameModified = $isNameModified , isNeedBind = $isNeedBind , canJump2Bind = $isCanJump2Bind , accountName = $accountName , oldUserName = $oldUserName"
    }

    constructor(i: Int, str: String?, str2: String?, str3: String?, z: Boolean, z2: Boolean, z3: Boolean) {
        resultCode = i
        resultMsg = str
        accountName = str2
        oldUserName = str3
        isNameModified = z3
        isNeedBind = z
        isCanJump2Bind = z2
    }

    companion object {
        fun fromJson(str: String?): AccountResult? {
            if (TextUtils.isEmpty(str)) {
                return null
            }
            val accountResult = AccountResult()
            try {
                val jSONObject = JSONObject(str)
                if (!jSONObject.isNull("accountName") && jSONObject["accountName"] !== JSONObject.NULL) {
                    accountResult.accountName = jSONObject.getString("accountName")
                }
                if (!jSONObject.isNull("oldUserName") && jSONObject["oldUserName"] !== JSONObject.NULL) {
                    accountResult.oldUserName = jSONObject.getString("oldUserName")
                }
                if (!jSONObject.isNull("isNeedBind") && jSONObject["isNeedBind"] !== JSONObject.NULL) {
                    accountResult.isNeedBind = jSONObject.getBoolean("isNeedBind")
                }
                if (!jSONObject.isNull("canJump2Bind") && jSONObject["canJump2Bind"] !== JSONObject.NULL) {
                    accountResult.isCanJump2Bind = jSONObject.getBoolean("canJump2Bind")
                }
                if (!jSONObject.isNull("isNameModified") && jSONObject["isNameModified"] !== JSONObject.NULL) {
                    accountResult.isNameModified = jSONObject.getBoolean("isNameModified")
                }
                if (!jSONObject.isNull("resultCode") && jSONObject["resultCode"] !== JSONObject.NULL) {
                    var i = jSONObject.getInt("resultCode")
                    if (i < 30000000) {
                        i += 30000000
                    }
                    accountResult.resultCode = i
                }
                if (!jSONObject.isNull("resultMsg") && jSONObject["resultMsg"] !== JSONObject.NULL) {
                    accountResult.resultMsg = jSONObject.getString("resultMsg")
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return accountResult
        }

        fun toJson(accountResult: AccountResult?): String? {
            return if (accountResult == null) {
                null
            } else try {
                val jSONObject = JSONObject()
                jSONObject.put("accountName", accountResult.accountName)
                jSONObject.put("oldUserName", accountResult.oldUserName)
                jSONObject.put("isNeedBind", accountResult.isNeedBind)
                jSONObject.put("canJump2Bind", accountResult.isCanJump2Bind)
                jSONObject.put("isNameModified", accountResult.isNameModified)
                jSONObject.put("resultCode", accountResult.resultCode)
                jSONObject.put("resultMsg", accountResult.resultMsg)
                jSONObject.toString()
            } catch (e: JSONException) {
                e.printStackTrace()
                null
            }
        }
    }
}