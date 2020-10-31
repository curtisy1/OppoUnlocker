package com.curtisy.oppounlocker.heytap.account

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.provider.SyncStateContract
import android.text.TextUtils
import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.helper.ApkInfoHelper
import com.curtisy.oppounlocker.heytap.BaseApp
import com.curtisy.oppounlocker.heytap.Constants
import com.curtisy.oppounlocker.heytap.models.SignInAccount
import com.curtisy.oppounlocker.heytap.models.AppInfo
import com.curtisy.oppounlocker.heytap.models.C0854b
import com.curtisy.oppounlocker.heytap.tasks.AsyncTaskC0857f
import com.curtisy.oppounlocker.heytap.tasks.AsyncTaskC0858g
import com.curtisy.oppounlocker.heytap.usercenter.UCCommonResponse
import com.curtisy.oppounlocker.heytap.usercenter.UCHeyTapAccountProvider
import com.curtisy.oppounlocker.heytap.usercenter.UCRuntimeEnvironment
import com.curtisy.oppounlocker.heytap.utils.AccountPrefUtils
import com.curtisy.oppounlocker.utilities.XORUtils
import org.json.JSONObject


class AccountAgent : IAccountAgent {
    var mLocalReqHandlerRef: Handler? = null
    var mSignInAccount: SignInAccount? = null

    @SuppressLint("StaticFieldLeak")
    open/* renamed from: com.heytap.usercenter.accountsdk.AccountAgentWrapper$a */
    class AsyncTaskC1266a(accountAgentWrapper: AccountAgent?, val f6915a: Context) : AsyncTask<Void?, Void?, C0854b?>() {
        /* Return type fixed from 'java.lang.Object' to match base method */ /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object[]] */
        // android.os.AsyncTask
        public override fun doInBackground(voidArr: Array<Void?>): C0854b? {
            return AccountAgentV320.m4617a(f6915a)
        }
    }

    private fun getVersionCode(context: Context): Int {
        if (mVersionCode < 0) {
            mVersionCode = getUserCenterVersionCode(context)
        }
        return mVersionCode
    }

    private fun isMultiAccountVersion(context: Context): Boolean {
        return !isSingleUserVersion(context) && ApkInfoHelper.m6318a(context, XORUtils.m6334a("kge&gxxg&{mz~akm&ikkg}f|")) > 0 && getVersionCode(context) >= 230
    }

    @SuppressLint("WrongConstant")
    private fun jumpToUserCenter(context: Context, str: String) {
        val intent = Intent(UCHeyTapAccountProvider.providerUsercenterFirstinXor8)
        intent.putExtra(Constants.EXTRA_ACTION_APPINFO_KEY, AppInfo.toJson(AccountHelper.getAppInfo(context, str)))
        intent.flags = 536870912
        if (context !is Activity) {
            intent.addFlags(268435456)
        }
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun postReqAccountInfoNetExceptionResult(context: Context, onreqaccountcallback: AccountNameTask.onReqAccountCallback<SignInAccount>) {
        AsyncTaskC0858g(this, context, context, onreqaccountcallback).execute()
    }

    /* access modifiers changed from: private */
    @SuppressLint("StaticFieldLeak")
    fun postReqAccountInfoResult(context: Context, uCCommonResponse: UCCommonResponse<JSONObject?>?, onreqaccountcallback: AccountNameTask.onReqAccountCallback<SignInAccount?>) {
        var errorResp: UCCommonResponse<JSONObject?>.ErrorResp
        mSignInAccount = SignInAccount()
        if (uCCommonResponse == null || !uCCommonResponse.isSuccess()) {
            val signInAccount: SignInAccount? = mSignInAccount
            signInAccount.isLogin = false
            if (uCCommonResponse == null || uCCommonResponse.error.also { errorResp = it } == null) {
                val signInAccount2: SignInAccount? = mSignInAccount
                signInAccount2.resultCode = "1003"
                signInAccount2.resultMsg = AccountNameProtocol.m6219b("1003")
            } else {
                signInAccount.resultCode = errorResp.code
                signInAccount.resultMsg = errorResp.message
            }
            onreqaccountcallback.onReqFinish(mSignInAccount)
            return
        }
        AsyncTaskC0857f(this, context, uCCommonResponse, context, onreqaccountcallback).execute(arrayOfNulls<Void>(0))
    }

    /* access modifiers changed from: private */
    fun reqAccountInfo(context: Context, z: Boolean, str: String?, onreqaccountcallback: AccountNameTask.onReqAccountCallback<SignInAccount>?) {
        if (onreqaccountcallback != null) {
            if (!NoNetworkUtil.isConnectNet(context)) {
                postReqAccountInfoNetExceptionResult(context, onreqaccountcallback)
                return
            }
            val eVar = C0856e(this, context, onreqaccountcallback)
            val accountNameParam = AccountNameParam(str, UCDeviceInfoUtil.m6312c(), UCRuntimeEnvironment.f7014a, z)
            UCDispatcherManager.getInstance().post(context, accountNameParam.getUrl(), accountNameParam.getRequestBody(), C0859j(eVar))
        }
    }

    private fun sendLoginSuccessMessage(handler: Handler?, context: Context) {
        if (handler != null) {
            if (isVersionUpV320(context)) {
                AccountAgentV320.m4618a(context, handler)
                return
            }
            val message = Message()
            message.obj = AccountPrefUtils.getUserEntity(context, null)
            handler.sendMessage(message)
        }
    }

    private fun sendUserMessage(handler: Handler?, userEntity: UserEntity) {
        if (handler != null) {
            val message = Message()
            message.obj = userEntity
            handler.sendMessage(message)
            mLocalReqHandlerRef = null
        }
    }

    // com.heytap.usercenter.accountsdk.AccountAgentInterface
    fun getAccountResult(context: Context, str: String?): AccountResult {
        initContextIfNeeded(context)

        if (!isLogin(context, str)) {
            val accountResult = AccountResult()
            accountResult.setCanJump2Bind(false)
            accountResult.setOldUserName(null)
            accountResult.setResultCode(Constants.REQ_NONE_ACCOUNT)
            accountResult.setResultMsg("usercenter has none account")
            return accountResult
        }
        val userName = getUserName(context, str)
        if (getVersionCode(context) >= 331) {
            return AccountNameAgent.m4620a(context)
        }
        val accountResult2 = AccountResult()
        accountResult2.setCanJump2Bind(false)
        accountResult2.setOldUserName(userName)
        accountResult2.setResultCode(Constants.REQ_NO_SUPPORT_ACCOUNTNAME)
        accountResult2.setResultMsg("usercenter low version")
        return accountResult2
    }

    // com.heytap.usercenter.accountsdk.AccountAgentInterface
    @SuppressLint("StaticFieldLeak")
    fun getSignInAccount(context: Context?, z: Boolean, onreqaccountcallback: AccountNameTask.onReqAccountCallback<SignInAccount?>?) {
        initContextIfNeeded(context)
        AccountAgentWrapper(this, context, onreqaccountcallback, context, z).execute(arrayOfNulls<Void>(0))
    }

    // com.heytap.usercenter.accountsdk.AccountAgentInterface
    fun getUserName(context: Context, str: String?): String {
        initContextIfNeeded(context)
        if (!isSingleUserVersion(context)) {
            return AccountAgent.m6157c(context, str)
        }
        if (!isVersionUpV320(context)) {
            return AccountPrefUtils.getNameByProvider(context)
        }
        val a: C0854b = AccountAgentV320.m4617a(context)
        return if (AccountAgentV320.m4619a(a)) a.f4555a else BuildConfig.FLAVOR
    }

    // com.heytap.usercenter.accountsdk.AccountAgentInterface
    fun hasUserCenterApp(context: Context): Boolean {
        return getVersionCode(context) > 0
    }

    // com.heytap.usercenter.accountsdk.AccountAgentInterface
    fun isLogin(context: Context, str: String?): Boolean {
        initContextIfNeeded(context)
        if (!isSingleUserVersion(context)) {
            return AccountAgent.m6152a(context, str)
        }
        if (isVersionUpV320(context)) {
            return AccountAgentV320.m4619a(AccountAgentV320.m4617a(context))
        }
        val userEntity: UserEntity = AccountPrefUtils.getUserEntity(context, null)
        return userEntity != null && !TextUtils.isEmpty(userEntity.mo6986b()) && !TextUtils.isEmpty(userEntity.mo6983a()) && userEntity.mo6988c() === 30001001
    }

    // com.heytap.usercenter.accountsdk.AccountAgentInterface
    fun isVersionUpV320(context: Context?): Boolean {
        initContextIfNeeded(context)
        if (mVersionCode < 0) {
            mVersionCode = getUserCenterVersionCode(context)
        }
        return mVersionCode >= 320
    }

    // com.heytap.usercenter.accountsdk.AccountAgentInterface
    fun reqLogout(context: Context) {
        if (isSingleUserVersion(context)) {
            jumpToUserCenter(context, BuildConfig.FLAVOR)
        } else {
            AccountAgent.m6158d(context, BuildConfig.FLAVOR)
        }
    }

    // com.heytap.usercenter.accountsdk.AccountAgentInterface
    fun reqReSignin(context: Context, handler: Handler?, str: String?) {
        initContextIfNeeded(context)
        if (isSingleUserVersion(context)) {
            try {
                mLocalReqHandlerRef = handler
                AccountHelper.startReqSignInActivity(context, str)
            } catch (unused: ActivityNotFoundException) {
                sendUserMessage(handler, UserEntity(Constants.REQ_USERCENTER_NOT_EXIST, "usercenter is not exist!", BuildConfig.FLAVOR, BuildConfig.FLAVOR))
            }
        } else {
            AccountAgent.m6155b(context, handler, str)
        }
    }

    // com.heytap.usercenter.accountsdk.AccountAgentInterface
    @SuppressLint("HandlerLeak")
    fun reqSignInAccount(context: Context, z: Boolean, str: String?, onreqaccountcallback: AccountNameTask.onReqAccountCallback<SignInAccount?>?) {
        initContextIfNeeded(context)
        if (onreqaccountcallback != null) {
            onreqaccountcallback.onReqStart()
            onreqaccountcallback.onReqLoading()
        }
        reqReSignin(context, HandlerC0855d(this, context, z, onreqaccountcallback), str)
    }

    // com.heytap.usercenter.accountsdk.AccountAgentInterface
    fun reqToken(context: Context, handler: Handler?, str: String?) {
        initContextIfNeeded(context)
        if (!isSingleUserVersion(context)) {
            mLocalReqHandlerRef = null
            if (!isSingleUserVersion(context)) {
                AccountAgent.m6149a()
            }
            AccountAgent.m6150a(context, handler, str)
        } else if (!isLogin(context, str)) {
            try {
                mLocalReqHandlerRef = handler
                AccountHelper.startReqTokenActivity(context, str, false)
            } catch (unused: ActivityNotFoundException) {
                sendUserMessage(handler, UserEntity(Constants.REQ_USERCENTER_NOT_EXIST, "usercenter is not exist!", BuildConfig.FLAVOR, BuildConfig.FLAVOR))
            }
        } else if (isVersionUpV320(context)) {
            AccountAgentV320.m4618a(context, handler)
        } else {
            sendLoginSuccessMessage(handler, context)
        }
    }

    // com.heytap.usercenter.accountsdk.AccountAgentInterface
    fun sendSingleReqMessage(userEntity: UserEntity?) {
        val handler: Handler? = mLocalReqHandlerRef
        if (handler != null) {
            sendUserMessage(handler, userEntity)
        }
    }

    // com.heytap.usercenter.accountsdk.AccountAgentInterface
    fun startAccountSettingActivity(context: Context, str: String?) {
        initContextIfNeeded(context)
        if (isSingleUserVersion(context)) {
            jumpToUserCenter(context, BuildConfig.FLAVOR)
        } else {
            AccountAgent.m6158d(context, BuildConfig.FLAVOR)
        }
    }

    companion object {
        const val SIGNINACCOUNT_EXPIRATIONTIME: Long = 600000
        var mVersionCode = -1

        fun sendExceptionMessage(handler: Handler, str: String?) {
            val accountResult = AccountResult(Constants.REQ_EXCEPTION, str, BuildConfig.FLAVOR, BuildConfig.FLAVOR, true, false, false)
            val obtain: Message = Message.obtain(null as Handler?, SyncStateContract.Constants.MSG_RESULT_FOR_REQ_BINDINFO as Int)
            val bundle = Bundle()
            bundle.putString(UCHeyTapAccountProvider.getExtraResultUsercenterBindInfo(), AccountResult.toJson(accountResult))
            obtain.setData(bundle)
            handler.sendMessage(obtain)
        }

        fun getUserCenterVersionCode(context: Context?): Int {
            val a: Int = ApkInfoHelper.m6318a(context, XORUtils.m6334a("kge&`mq|ix&}{mzkmf|mz"))
            return if (a > 0) {
                a
            } else ApkInfoHelper.m6318a(context, XORUtils.m6334a("kge&gxxg&}{mzkmf|mz"))
        }

        fun initContextIfNeeded(context: Context?) {
            if (BaseApp.f7020a == null) {
                BaseApp.m6293a(context)
            }
        }

        private fun isSingleUserVersion(context: Context): Boolean {
            if (mVersionCode < 0) {
                mVersionCode = getUserCenterVersionCode(context)
            }
            return mVersionCode >= 300
        }

        fun getHeytapToken(context: Context, str: String?): String {
            initContextIfNeeded(context)
            if (!isSingleUserVersion(context)) {
                return AccountAgent.m6154b(context, str)
            }
            if (!isVersionUpV320(context)) {
                return AccountPrefUtils.getTokenByProvider(context)
            }
            val a = AccountAgentV320.m4617a(context)
            return if (AccountAgentV320.m4619a(a)) a.f4556b else BuildConfig.FLAVOR
        }
    }
}