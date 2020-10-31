package com.curtisy.oppounlocker.heytap.dns

import android.content.Context
import java.net.InetAddress

class LocalDns(context: Context) : DnsImp(context) {
    override val dnsType: Int
        get() = DnsInfo.TYPE_LOCAL

    // neton.client.dns.Dns
//    fun lookup(str: String): IpInfo {
//        val addressInfo = getAddressInfo(str)
//        synchronized(addressInfo) {
//            var ipInfo: IpInfo = addressInfo.getIpInfo()
//            return if (ipInfo == null || !ipInfo.checkSelect()) {
//                val ipInfoList: List<IpInfo> = addressInfo.getIpInfoList()
//                if (ipInfoList != null && ipInfoList.size > 0) {
//                    if (checkNeedDelete(addressInfo)) {
//                        delete(str)
//                    } else {
//                        ipInfo = select(ipInfoList)!!
//                        if (ipInfo != null) {
//                            return ipInfo
//                        }
//                        delete(str)
//                    }
//                }
//                val asList = InetAddress.getAllByName(str).toList()
//                if (asList != null && asList.size > 0) {
//                    val arrayList = ArrayList<Any>()
//                    for (inetAddress in asList) {
//                        val ipInfo2 = IpInfo(str, dnsType)
//                        ipInfo2.ip = inetAddress.hostAddress
//                        arrayList.add(ipInfo2)
//                    }
//                    val select = select(arrayList)
//                    saveAddressInfo(str, arrayList, dnsType)
//                    ipInfo = select!!
//                }
//                ipInfo
//            } else {
//                ipInfo
//            }
//        }
//    }
//
//    fun select(list: List<IpInfo>): IpInfo? {
//        val arrayList = ArrayList<Any>()
//        for (ipInfo in list) {
//            if (ipInfo.checkSelect()) {
//                arrayList.add(ipInfo)
//            }
//        }
//        val size: Int = arrayList.size
//        if (size == 0) {
//            return null
//        }
//        return if (size > 0) {
//            arrayList[MathUtils.random(size)] as IpInfo
//        } else arrayList[0] as IpInfo
//    }

    init {
        load()
    }
}
