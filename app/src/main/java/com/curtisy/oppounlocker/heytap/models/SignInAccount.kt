package com.curtisy.oppounlocker.heytap.models

import com.curtisy.oppounlocker.heytap.models.BasicUserInfo

class SignInAccount {
    var deviceId: String? = null
    var expirationTime: Long = 0
    var isLogin = false
    var jsonString: String? = null
    var resultCode: String? = null
    var resultMsg: String? = null
    var token: String? = null
    var userInfo: BasicUserInfo? = null

    override fun toString(): String {
        val str: String
        val sb = StringBuilder("SignInAccount{isLogin=")
        sb.append(isLogin)
        sb.append(", deviceId='")
        sb.append(deviceId)
        sb.append('\'')
        sb.append(", token='")
        sb.append(token)
        sb.append('\'')
        sb.append(", userInfo=")
        val basicUserInfo: BasicUserInfo? = userInfo
        str = if (basicUserInfo?.toJson() == null) {
            "null"
        } else {
            basicUserInfo.toJson()!!
        }
        sb.append(str)
        sb.append(", jsonString='")
        sb.append(jsonString)
        sb.append('\'')
        sb.append(", resultCode=")
        sb.append(resultCode)
        sb.append(", resultMsg='")
        sb.append(resultMsg)
        sb.append('\'')
        sb.append(", expirationTime=")
        sb.append(expirationTime)
        sb.append('}')
        return sb.toString()
    }
}