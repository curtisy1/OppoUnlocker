package com.curtisy.oppounlocker.heytap.tasks

import android.content.Context
import android.text.TextUtils
import com.curtisy.oppounlocker.heytap.account.AccountAgent
import com.curtisy.oppounlocker.heytap.account.AccountNameProtocol
import com.curtisy.oppounlocker.heytap.account.AccountNameTask.onReqAccountCallback
import com.curtisy.oppounlocker.heytap.models.BasicUserInfo
import com.curtisy.oppounlocker.heytap.models.C0854b
import com.curtisy.oppounlocker.heytap.models.SignInAccount
import com.curtisy.oppounlocker.heytap.utils.AccountPrefUtils


class AsyncTaskC0858g(accountAgentWrapper: AccountAgent, context: Context, context2: Context, onreqaccountcallback: onReqAccountCallback<*>) : AccountAgent.AsyncTaskC1266a(accountAgentWrapper, context) {
    /* renamed from: b */
    /* synthetic */ val f4574b: Context

    /* renamed from: c */
    /* synthetic */ val f4575c: onReqAccountCallback<*>

    /* renamed from: d */
    /* synthetic */ val f4576d: AccountAgent

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    // android.os.AsyncTask
    override fun onPostExecute(bVar: C0854b?) {
        var userInfo: BasicUserInfo
        super.onPostExecute(bVar)
        f4576d.mSignInAccount = SignInAccount()
        if (bVar == null || TextUtils.isEmpty(bVar.f4555a) && TextUtils.isEmpty(bVar.f4557c) || AccountPrefUtils.getUserInfo(f4574b, bVar.f4557c).also { userInfo = it } == null) {
            val signInAccount = f4576d.mSignInAccount
            signInAccount.isLogin = false
            signInAccount.resultCode = "2001"
            signInAccount.resultMsg = AccountNameProtocol.m6219b("2001")
            f4575c.onReqFinish(f4576d.mSignInAccount)
            return
        }
        val signInAccount2 = f4576d.mSignInAccount
        signInAccount2.isLogin = true
        signInAccount2.resultCode = "2000"
        signInAccount2.resultMsg = AccountNameProtocol.m6219b("2000")
        val signInAccount3: SignInAccount = f4576d.mSignInAccount
        signInAccount3.userInfo = userInfo
        signInAccount3.token = bVar.f4556b
        signInAccount3.deviceId = bVar.f4558d
        signInAccount3.jsonString = userInfo.toJson()
        f4575c.onReqFinish(f4576d.mSignInAccount)
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    init {
        f4576d = accountAgentWrapper
        f4574b = context2
        f4575c = onreqaccountcallback
    }
}