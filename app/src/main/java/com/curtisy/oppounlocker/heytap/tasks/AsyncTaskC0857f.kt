package com.curtisy.oppounlocker.heytap.tasks

import android.content.Context
import android.text.TextUtils
import com.curtisy.oppounlocker.heytap.account.AccountAgent
import com.curtisy.oppounlocker.heytap.account.AccountNameProtocol
import com.curtisy.oppounlocker.heytap.account.AccountNameTask.onReqAccountCallback
import com.curtisy.oppounlocker.heytap.models.BasicUserInfo
import com.curtisy.oppounlocker.heytap.models.C0854b
import com.curtisy.oppounlocker.heytap.models.SignInAccount
import com.curtisy.oppounlocker.heytap.usercenter.UCCommonResponse
import com.curtisy.oppounlocker.heytap.utils.AccountPrefUtils


class AsyncTaskC0857f(accountAgentWrapper: AccountAgent, context: Context, uCCommonResponse: UCCommonResponse<*>, context2: Context, onreqaccountcallback: onReqAccountCallback<*>) : AccountAgent.AsyncTaskC1266a(accountAgentWrapper, context) {
    /* renamed from: b */
    /* synthetic */ val f4570b: UCCommonResponse<*>

    /* renamed from: c */
    /* synthetic */ val f4571c: Context

    /* renamed from: d */
    /* synthetic */ val f4572d: onReqAccountCallback<*>

    /* renamed from: e */
    /* synthetic */ val f4573e: AccountAgent

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    // android.os.AsyncTask
    override fun onPostExecute(bVar: C0854b?) {
        super.onPostExecute(bVar)
        if (bVar == null) {
            val signInAccount: SignInAccount = f4573e.mSignInAccount
            signInAccount.isLogin = false
            signInAccount.resultCode = "1002"
            signInAccount.resultMsg = AccountNameProtocol.m6219b("1002")
        } else {
            val signInAccount2: SignInAccount = f4573e.mSignInAccount
            signInAccount2.isLogin = true
            signInAccount2.resultCode = "1000"
            signInAccount2.resultMsg = AccountNameProtocol.m6219b("1000")
            val signInAccount3: SignInAccount = f4573e.mSignInAccount
            signInAccount3.token = bVar.f4556b
            signInAccount3.deviceId = bVar.f4558d
            signInAccount3.userInfo = BasicUserInfo.fromJson(f4570b.data.toString())
            if (TextUtils.isEmpty(f4573e.mSignInAccount.userInfo.ssoid)) {
                f4573e.mSignInAccount.userInfo.ssoid = bVar.f4557c
            }
            f4573e.mSignInAccount.jsonString = f4570b.data.toString()
            f4573e.mSignInAccount.expirationTime = System.currentTimeMillis() + 600000
            val context: Context = f4571c
            val basicUserInfo: BasicUserInfo = f4573e.mSignInAccount.userInfo
            AccountPrefUtils.saveUserInfo(context, basicUserInfo.ssoid!!, basicUserInfo)
        }
        f4572d.onReqFinish(f4573e.mSignInAccount)
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    init {
        f4573e = accountAgentWrapper
        f4570b = uCCommonResponse
        f4571c = context2
        f4572d = onreqaccountcallback
    }
}
