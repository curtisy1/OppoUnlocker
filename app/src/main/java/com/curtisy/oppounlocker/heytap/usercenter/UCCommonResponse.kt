package com.curtisy.oppounlocker.heytap.usercenter

import android.text.TextUtils
import com.curtisy.oppounlocker.heytap.usercenter.UCUtils.m6331a
import com.curtisy.oppounlocker.heytap.usercenter.UCUtils.m6332b
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset


abstract class UCCommonResponse<T> : IUCBaseResult {
    var data: T? = null
    var error: ErrorResp? = null
    var success = false

    inner class ErrorResp {
        var code: String = ""
        var message: String = ""
    }

    val code: String
        get() {
            val errorResp = error
            return errorResp?.code ?: "0"
        }
    val message: String
        get() {
            val errorResp = error
            return errorResp?.message ?: "nothing happen"
        }

    fun isSuccess(): Boolean {
        return success
    }

    fun loadCommonJson(jSONObject: JSONObject?, uCCommonResponse: UCCommonResponse<T>?) {
        if (jSONObject != null && uCCommonResponse != null) {
            try {
                uCCommonResponse.success = m6332b(jSONObject, "success")
                val errorResp: ErrorResp = ErrorResp()
                val a = m6331a(jSONObject, "error")
                if (!TextUtils.isEmpty(a)) {
                    val jSONObject2 = JSONObject(a)
                    errorResp.code = m6331a(jSONObject2, "code")
                    errorResp.message = m6331a(jSONObject2, "message")
                    uCCommonResponse.error = errorResp
                }
                val a2 = m6331a(jSONObject, "data")
                if (!TextUtils.isEmpty(a2)) {
                    try {
                        uCCommonResponse.data = parserData(JSONObject(a2))
                    } catch (e: JSONException) {
                        uCCommonResponse.data = parserData(a2)
                    }
                }
            } catch (e2: JSONException) {
                e2.printStackTrace()
            }
        }
    }

    fun parserData(str: String): T? {
        return null
    }

    abstract fun parserData(jSONObject: JSONObject): T

    // com.heytap.usercenter.accountsdk.http.UCBaseResult
    override fun parseNetworkResponse(bArr: ByteArray?): UCCommonResponse<T>? {
        return try {
            val str = String(bArr!!, Charset.forName("utf-8"))
            if (TextUtils.isEmpty(str)) {
                return null
            }
            loadCommonJson(JSONObject(str), this)
            this
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            null
        } catch (e2: JSONException) {
            e2.printStackTrace()
            null
        }
    }
}