package com.curtisy.oppounlocker.heytap.usercenter

import com.curtisy.oppounlocker.heytap.account.AccountNameProtocol
import com.curtisy.oppounlocker.heytap.account.AccountNameTask.onReqAccountCallback
import com.curtisy.oppounlocker.heytap.usercenter.UCCommonResponse
import org.json.JSONObject


class C0859j(val f4578a: onReqAccountCallback<UCCommonResponse<JSONObject>?>) : IUCRequestCallBack<UCCommonResponse<JSONObject?>?> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    // com.heytap.usercenter.accountsdk.http.UCRequestCallBack
    fun onReqFinish(uCCommonResponse: UCCommonResponse<JSONObject>) {
        val onreqaccountcallback = f4578a
        onreqaccountcallback.onReqFinish(uCCommonResponse)
    }

    // com.heytap.usercenter.accountsdk.http.UCRequestCallBack
    override fun onReqLoading(bArr: ByteArray?): UCCommonResponse<JSONObject>? {
        val onreqaccountcallback = f4578a
        onreqaccountcallback.onReqLoading()
        return AccountNameProtocol().parseNetworkResponse(bArr)
    }

    // com.heytap.usercenter.accountsdk.http.UCRequestCallBack
    override fun onReqStart() {
        val onreqaccountcallback = f4578a
        onreqaccountcallback.onReqStart()
    }
}