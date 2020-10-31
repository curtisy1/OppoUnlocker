package com.curtisy.oppounlocker.heytap.account

import android.content.Context
import android.os.Handler
import com.curtisy.oppounlocker.heytap.account.AccountResult
import com.curtisy.oppounlocker.heytap.models.SignInAccount
import com.curtisy.oppounlocker.heytap.models.UserEntity


interface IAccountAgent {
    fun getAccountResult(context: Context?, str: String?): AccountResult?
    fun getSignInAccount(context: Context?, z: Boolean, onreqaccountcallback: AccountNameTask.onReqAccountCallback<SignInAccount?>?)
    fun getToken(context: Context?, str: String?): String?

    @Deprecated("")
    fun getUserName(context: Context?, str: String?): String?
    fun hasUserCenterApp(context: Context?): Boolean
    fun isLogin(context: Context?, str: String?): Boolean
    fun isVersionUpV320(context: Context?): Boolean
    fun reqLogout(context: Context?)
    fun reqReSignin(context: Context?, handler: Handler?, str: String?)
    fun reqSignInAccount(context: Context?, z: Boolean, str: String?, onreqaccountcallback: AccountNameTask.onReqAccountCallback<SignInAccount?>?)
    fun reqToken(context: Context?, handler: Handler?, str: String?)
    fun sendSingleReqMessage(userEntity: UserEntity?)
    fun startAccountSettingActivity(context: Context?, str: String?)
}