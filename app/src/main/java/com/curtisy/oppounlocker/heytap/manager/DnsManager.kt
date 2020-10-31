package com.curtisy.oppounlocker.heytap.manager

import android.content.Context
import android.text.TextUtils
import com.curtisy.oppounlocker.heytap.Constants
import com.curtisy.oppounlocker.heytap.dns.HttpDns
import com.curtisy.oppounlocker.heytap.dns.IpInfo
import com.curtisy.oppounlocker.heytap.dns.LocalDns

class DnsManager private constructor() {
    private var mHttpDns: HttpDns? = null
    private var mLocalDns: LocalDns? = null

    fun init(context: Context) {
        mHttpDns = HttpDns(context)
        mLocalDns = LocalDns(context)
    }

    // neton.client.dns.Dns
//    fun lookup(str: String): IpInfo? {
//        var ipInfo: IpInfo? = null
//        if (TextUtils.isEmpty(str)) {
//            return null
//        }
//        val intData = ConfigManager.getInstance().getIntData(Constants.KEY_DNS_MODE, 1)
//        if (intData == 0) {
//            ipInfo = mHttpDns?.lookup(str)
//            if (ipInfo == null || !ipInfo.isIpInfoValid) {
//                ipInfo = mLocalDns.lookup(str)
//            }
//        } else if (1 == intData) {
//            ipInfo = mLocalDns.lookup(str)
//        } else if (2 == intData) {
//            ipInfo = mHttpDns?.lookup(str)
//        }
//        return ipInfo
//    }

    companion object {
        val instance = DnsManager()
    }
}
