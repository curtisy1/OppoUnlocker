package com.curtisy.oppounlocker.heytap.account

import com.curtisy.oppounlocker.heytap.annotations.NoSign
import com.curtisy.oppounlocker.heytap.usercenter.C0859j
import com.curtisy.oppounlocker.heytap.usercenter.UCHeyTapAccountProvider
import com.curtisy.oppounlocker.heytap.usercenter.UCSignHelper
import com.curtisy.oppounlocker.heytap.usercenter.UCUtils

class AccountNameParam(var userToken: String?, @field:NoSign var isRealme: Boolean, @field:NoSign var isExp: Boolean, @field:NoSign var isRelease: Boolean) : UCBaseRequest() {
    @NoSign
    var sign: String?
    var timestamp: Long

    /* synthetic */   constructor(str: String?, z: Boolean, z2: Boolean, z3: Boolean, jVar: C0859j?) : this(str, z, z2, z3) {}

    // com.heytap.usercenter.accountsdk.http.UCBaseRequest
    val url: String
        get() {
            if (!isRelease) {
                return UCHeyTapAccountProvider.providerTestUrlXor8
            }
            if (isRealme) {
                return UCHeyTapAccountProvider.providerRealmeHostUrlXor8
            }
            return if (isExp) UCHeyTapAccountProvider.providerExpReleaseUrlXor8 else UCHeyTapAccountProvider.providerReleaseUrlXor8
        }

    init {
        timestamp = System.currentTimeMillis()
        sign = UCUtils.m6330a(UCSignHelper.m6314a(this))
    }
}