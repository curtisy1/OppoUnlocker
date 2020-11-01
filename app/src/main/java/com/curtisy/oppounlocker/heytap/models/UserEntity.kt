package com.curtisy.oppounlocker.heytap.models

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import android.text.TextUtils
import com.curtisy.oppounlocker.BuildConfig
import org.json.JSONException
import org.json.JSONObject


class UserEntity : Parcelable {
    /* renamed from: a */
    private var f6916a: Int

    /* renamed from: b */
    private var f6917b: String

    /* renamed from: c */
    private var f6918c: String

    /* renamed from: d */
    private var f6919d: String
    override fun describeContents(): Int {
        return 0
    }

    constructor() {
        f6916a = 0
        f6917b = ""
        f6918c = ""
        f6919d = ""
    }

    constructor(i: Int, str: String, str2: String, str3: String) {
        f6916a = i
        f6917b = str
        f6918c = str2
        f6919d = str3
    }

    /* renamed from: a */
    fun mo6983a(): String {
        return f6918c
    }

    /* renamed from: a */
    fun mo6985a(str: String) {
        f6918c = str
    }

    /* renamed from: b */
    fun mo6986b(): String {
        return f6919d
    }

    /* renamed from: b */
    fun mo6987b(str: String) {
        f6919d = str
    }

    /* renamed from: a */
    fun mo6984a(i: Int) {
        f6916a = i
    }

    /* renamed from: c */
    fun mo6988c(): Int {
        return f6916a
    }

    /* renamed from: c */
    fun mo6989c(str: String) {
        f6917b = str
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(f6916a)
        parcel.writeString(f6917b)
        parcel.writeString(f6918c)
        parcel.writeString(f6919d)
    }

    override fun toString(): String {
        return "{UserEntity : [result = $f6916a],[resultMsg = $f6917b],[username = $f6918c],[authToken = $f6919d}"
    }

    companion object {
        val CREATOR: Creator<UserEntity?> = C1274j()

        /* renamed from: d */
        fun m6222d(str: String?): UserEntity? {
            if (TextUtils.isEmpty(str)) {
                return null
            }
            val userEntity = UserEntity()
            return try {
                val jSONObject = JSONObject(str)
                if (!jSONObject.isNull("result") && jSONObject["result"] !== JSONObject.NULL) {
                    userEntity.f6916a = jSONObject.getInt("result")
                }
                if (!jSONObject.isNull("resultMsg") && jSONObject["resultMsg"] !== JSONObject.NULL) {
                    userEntity.f6917b = jSONObject.getString("resultMsg")
                }
                if (!jSONObject.isNull("username") && jSONObject["username"] !== JSONObject.NULL) {
                    userEntity.f6918c = jSONObject.getString("username")
                }
                if (!jSONObject.isNull("authToken") && jSONObject["authToken"] !== JSONObject.NULL) {
                    userEntity.f6919d = jSONObject.getString("authToken")
                }
                userEntity
            } catch (e: JSONException) {
                e.printStackTrace()
                null
            }
        }

        /* renamed from: a */
        fun m6221a(userEntity: UserEntity?): String? {
            return if (userEntity == null) {
                null
            } else try {
                val jSONObject = JSONObject()
                jSONObject.put("result", userEntity.f6916a)
                jSONObject.put("resultMsg", userEntity.f6917b)
                jSONObject.put("username", userEntity.f6918c)
                jSONObject.put("authToken", userEntity.f6919d)
                jSONObject.toString()
            } catch (e: JSONException) {
                e.printStackTrace()
                null
            }
        }
    }
}