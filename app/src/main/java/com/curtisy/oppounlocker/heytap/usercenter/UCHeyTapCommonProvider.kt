package com.curtisy.oppounlocker.heytap.usercenter

import com.curtisy.oppounlocker.utilities.XORUtils

object UCHeyTapCommonProvider {
    /* renamed from: a */
    fun m6288a(str: String?): String {
        return XORUtils.hash(str!!)
    }
}