package com.curtisy.oppounlocker.data

import android.content.Context
import com.curtisy.oppounlocker.heytap.account.AccountAgent
import com.curtisy.oppounlocker.service.RequestService
import com.curtisy.oppounlocker.utilities.Utils


class C1234b {
    private var chipId: String? = null

    private var udid: String? = null

    private var model: String? = null

    private var otaVersion: String? = null

    private var token: String? = null

    private var clientLockStatus = 0

    fun mo6785a(context: Context?) {
        udid = "1234567890" // TODO: This used to be getImei. But only system apps can get the IMEI.. unless there's a worker profile. Investigate
        model = "PDEM30"
        otaVersion = "PDEM10_11.A.17_0470_202009091604"

        val context2 = RequestService.context

        token = AccountAgent.getHeytapToken(context2, context2.packageName) // TODO: HeyTap. Either recreate auth or manually read and store
        chipId = Utils.m6048c() // TODO: We don't have access to this either. Brute-force?
        clientLockStatus = 0
    }
}