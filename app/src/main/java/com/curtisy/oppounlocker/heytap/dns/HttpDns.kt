package com.curtisy.oppounlocker.heytap.dns

import android.content.Context
import android.text.TextUtils
import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.heytap.Constants
import com.curtisy.oppounlocker.heytap.helper.NetHelper
import com.curtisy.oppounlocker.heytap.helper.NetHelper.getCarrierName
import com.curtisy.oppounlocker.heytap.helper.NetHelper.isConnectNet
import com.curtisy.oppounlocker.heytap.helper.NetHelper.isWifiConnecting
import com.curtisy.oppounlocker.heytap.manager.ConfigManager
import com.curtisy.oppounlocker.heytap.utils.ApkInfoUtil.isRegionCN
import com.curtisy.oppounlocker.heytap.utils.ThreadPoolUtil
import kotlin.collections.ArrayList


class HttpDns(context: Context) : DnsImp(context) {
    // neton.client.dns.DnsImp
    override val dnsType: Int
        get() = DnsInfo.TYPE_HTTP

    // neton.client.dns.Dns
//    fun lookup(str: String): IpInfo? {
//        val addressInfo = getAddressInfo(str)
//        synchronized(addressInfo) {
//            var ipInfo: IpInfo? = addressInfo.getIpInfo()
//            if (ipInfo == null || !ipInfo.checkSelect()) {
//                val ipInfoList: List<IpInfo> = addressInfo.getIpInfoList()
//                if (ipInfoList != null && ipInfoList.size > 0) {
//                    if (checkNeedDelete(addressInfo)) {
//                        delete(str)
//                    } else {
//                        ipInfo = select(ipInfoList)
//                        if (ipInfo != null) {
//                            return ipInfo
//                        }
//                        delete(str)
//                    }
//                }
//                if (isConnectNet(this.mContext) && !addressInfo.isLooking()) {
//                    addressInfo.setLooking(true)
//                    ThreadPoolUtil.executeSingle(Runnable
//                    /* class neton.client.dns.HttpDns.RunnableC13081 */
//                    {
//                        try {
//                            Thread.sleep(1000)
//                        } catch (e: InterruptedException) {
//                            e.printStackTrace()
//                        }
//                        initAddressInfo(this@HttpDns.mContext, str)
//                    })
//                }
//                return ipInfo
//            }
//            return ipInfo
//        }
//    }
//
//    fun initAddressInfo(context: Context?, str: String) {
//        var listData = ConfigManager.getInstance().getListData(if (isRegionCN) Constants.KEY_HTTP_DNS_LIST else Constants.KEY_FOREIGN_HTTP_DNS_LIST)
//        if (listData == null || listData.isEmpty()) {
//            listData = (if (isRegionCN) Constants.DEFAULT_HTTP_DNS_LIST else Constants.DEFAULT_FOREIGN_HTTP_DNS_LIST).toList() as ArrayList<String>
//        }
//        if (listData != null || !TextUtils.isEmpty("")) {
//            val arrayList = ArrayList<Any>()
//            var z = false
//            var i = 0
//            while (listData != null && i < listData.size) {
//                arrayList.add(listData[i])
//                i++
//            }
//            while (true) {
//                if (arrayList.size <= 0) {
//                    break
//                }
//                val random: Int = MathUtils.random(arrayList.size)
//                val str2 = arrayList[random] as String
//                val requestDns: List<String> = HttpRequestHelper.getRequestDns(context, str2, str)
//                if (requestDns == null || requestDns.size == 0) {
//                    arrayList.remove(random)
//                } else {
//                    val arrayList2 = ArrayList<Any>()
//                    for (str3 in requestDns) {
//                        arrayList2.add(IpInfo.parseIpInfo(str, dnsType, str3))
//                    }
//                    saveAddressInfo(str, arrayList2, dnsType)
//                    z = true
//                }
//            }
//            if (!z) {
//                val requestDns2: List<String> = HttpRequestHelper.getRequestDns(context, "", str)
//                val arrayList3 = ArrayList<Any>()
//                if (requestDns2 != null && requestDns2.size > 0) {
//                    for (str4 in requestDns2) {
//                        arrayList3.add(IpInfo.parseIpInfo(str, dnsType, str4))
//                    }
//                    saveAddressInfo(str, arrayList3, dnsType)
//                }
//            }
//        }
//    }
//
//    fun select(list: List<IpInfo>): IpInfo? {
//        val str: String
//        val ipInfo: IpInfo
//        val arrayList = ArrayList<Any>()
//        for (ipInfo2 in list) {
//            if (ipInfo2.checkSelect()) {
//                arrayList.add(ipInfo2)
//            }
//        }
//        val size: Int = arrayList.size
//        if (size == 0) {
//            return null
//        }
//        val stringData = ConfigManager.getInstance().getStringData(Constants.KEY_HEADER_SET)
//        str = if (isWifiConnecting(this.mContext)) {
//            NetHelper.CARRIER_BGP
//        } else {
//            getCarrierName(this.mContext)
//        }
//        val arrayList2 = ArrayList<Any>()
//        for (i in 0 until size) {
//            val ipInfo3: IpInfo = arrayList[i] as IpInfo
//            if (ipInfo3 != null && (TextUtils.isEmpty(stringData) || TextUtils.isEmpty(ipInfo3.local) || stringData.equals(ipInfo3.local, ignoreCase = true)) && (NetHelper.CARRIER_BGP.equals(str, ignoreCase = true) || NetHelper.CARRIER_BGP.equals(ipInfo3.sp, ignoreCase = true) || str.equals(ipInfo3.sp, ignoreCase = true))) {
//                val weight: Int = (arrayList[i] as IpInfo).weight
//                for (i2 in 0 until weight) {
//                    arrayList2.add(arrayList[i])
//                }
//            }
//        }
//        if (arrayList2.size === 0) {
//            return null
//        }
//        ipInfo = if (arrayList2.size > 1) {
//            arrayList2[MathUtils.random(arrayList2.size())] as IpInfo
//        } else {
//            arrayList2[0] as IpInfo
//        }
//        return ipInfo
//    }

    init {
        load()
    }
}
