package com.curtisy.oppounlocker.heytap.usercenter

import android.text.TextUtils
import com.curtisy.oppounlocker.BuildConfig
import okio.ByteString.Companion.toByteString
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.lang.AssertionError
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


object UCUtils {
    /* renamed from: a */
    fun m6330a(str: String): String? {
        return if (TextUtils.isEmpty(str)) {
            ""
        } else try {
            val digest = MessageDigest.getInstance("MD5").digest(str.toByteArray(charset("UTF-8")))
            if (digest.isNotEmpty()) {
                return digest.toByteString(0, digest.size).base64()
            }
            null
        } catch (e: NoSuchAlgorithmException) {
            throw AssertionError(e)
        } catch (e2: UnsupportedEncodingException) {
            e2.printStackTrace()
            null
        }
    }

    /* renamed from: a */
    fun m6331a(jSONObject: JSONObject, str: String): String {
        return if (m6333c(jSONObject, str)) {
            ""
        } else jSONObject.getString(str)
    }

    /* renamed from: b */
    fun m6332b(jSONObject: JSONObject, str: String): Boolean {
        return if (m6333c(jSONObject, str)) {
            false
        } else jSONObject.getBoolean(str)
    }

    /* renamed from: c */
    private fun m6333c(jSONObject: JSONObject?, str: String): Boolean {
        return jSONObject == null || TextUtils.isEmpty(str) || jSONObject.isNull(str) || jSONObject[str] === JSONObject.NULL
    }
}