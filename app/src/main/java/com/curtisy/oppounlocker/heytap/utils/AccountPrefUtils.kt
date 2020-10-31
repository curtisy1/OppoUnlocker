package com.curtisy.oppounlocker.heytap.utils

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.heytap.Constants
import com.curtisy.oppounlocker.heytap.helper.Base64Helper
import com.curtisy.oppounlocker.heytap.models.BasicUserInfo
import com.curtisy.oppounlocker.heytap.models.UserEntity


object AccountPrefUtils {
    const val USERCENTER_ACCOUNT_SP_KEY = "usercenter_account_key"
    fun clearData(context: Context) {
        getPackageSharedPreferences(context).edit().clear().apply()
    }

    fun getNameByProvider(context: Context?): String? {
        val userEntity = getUserEntity(context, null)
        return userEntity?.mo6983a()
    }

    fun getPackageSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.getPackageName() + Constants.USERCENTER_SP_SUFFIX, 0)
    }

    fun getString(context: Context?, str: String?): String? {
        return getString(context, str, null)
    }

    fun getTokenByProvider(context: Context?): String? {
        val userEntity = getUserEntity(context, null)
        return userEntity?.mo6986b()
    }

    fun getUserEntity(context: Context?, userEntity: UserEntity?): UserEntity? {
        val string = getString(context, USERCENTER_ACCOUNT_SP_KEY, null)
        return if (!TextUtils.isEmpty(string)) {
            UserEntity.m6222d(string)
        } else null
    }

    fun getUserInfo(context: Context?, str: String): BasicUserInfo? {
        return if (TextUtils.isEmpty(str)) {
            null
        } else BasicUserInfo.fromJson(Base64Helper.base64Decode(getString(context, Base64Helper.base64Encode(str), null)))
    }

    fun saveUserEntity(context: Context, userEntity: UserEntity?) {
        if (userEntity != null) {
            setString(context, USERCENTER_ACCOUNT_SP_KEY, UserEntity.m6221a(userEntity))
        }
    }

    fun saveUserInfo(context: Context, str: String, basicUserInfo: BasicUserInfo?) {
        if (!TextUtils.isEmpty(str) && basicUserInfo != null) {
            setString(context, Base64Helper.base64Encode(str), basicUserInfo.toJson()?.let { Base64Helper.base64Encode(it) })
        }
    }

    fun setName(context: Context, str: String?) {
        val userEntity = getUserEntity(context, null)
        if (userEntity != null) {
            userEntity.mo6985a(str!!)
            saveUserEntity(context, userEntity)
        }
    }

    fun setString(context: Context, str: String?, str2: String?) {
        getPackageSharedPreferences(context).edit().putString(str, str2).apply()
    }

    fun getString(context: Context?, str: String?, str2: String?): String? {
        if (context != null) {
            return getPackageSharedPreferences(context).getString(str, str2)
        }
        return BuildConfig.FLAVOR
    }
}