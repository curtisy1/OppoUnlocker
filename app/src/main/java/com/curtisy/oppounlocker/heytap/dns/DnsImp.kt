package com.curtisy.oppounlocker.heytap.dns

import android.content.Context
import com.curtisy.oppounlocker.heytap.utils.DBUtil
import com.curtisy.oppounlocker.heytap.utils.ThreadPoolUtil


abstract class DnsImp(context: Context) {
    protected var mAddressInfos: MutableMap<String, AddressInfo> = HashMap()
    protected var mContext: Context
    abstract val dnsType: Int

    fun load() {
        ThreadPoolUtil.execute(Runnable
        /* class neton.client.dns.DnsImp.RunnableC13051 */
        {
            synchronized(mAddressInfos) {
                val addressInfoList = DBUtil.getAddressInfoList(mContext, dnsType)
                if (addressInfoList != null && addressInfoList.size > 0) {
                    for (addressInfo in addressInfoList) {
                        val ipInfoList = DBUtil.getIpInfoList(mContext, dnsType, addressInfo.getHost())
                        if (ipInfoList != null) {
                            addressInfo.setIpInfoList(ipInfoList)
                        }
                        mAddressInfos[addressInfo.getHost()] = addressInfo
                    }
                }
            }
        })
    }

//    fun checkNeedDelete(addressInfo: AddressInfo): Boolean {
//        if (addressInfo.getTimeStamp() === 0 || addressInfo.getTimeStamp() >= System.currentTimeMillis()) {
//            val ipInfoList = addressInfo.getIpInfoList()
//            if (ipInfoList != null && ipInfoList.size > 0) {
//                var i = 0
//                for (ipInfo in ipInfoList) {
//                    i += ipInfo.getFailCount()
//                }
//                if (i.toFloat() > ipInfoList.size.toFloat() * DELETE_WEIGHT) {
//                    return true
//                }
//            }
//            return false
//        }
//        return true
//    }
//
//    fun getAddressInfo(str: String): AddressInfo? {
//        var addressInfo: AddressInfo?
//        synchronized(mAddressInfos) {
//            if (!mAddressInfos.containsKey(str)) {
//                mAddressInfos[str] = AddressInfo(str, dnsType)
//            }
//            addressInfo = mAddressInfos[str]
//        }
//        return addressInfo
//    }
//
//    fun saveAddressInfo(str: String, list: List<IpInfo?>?, i: Int) {
//        val addressInfo = getAddressInfo(str)
//        if (list != null && list.size != 0) {
//            ThreadPoolUtil.executeSingle(Runnable
//            /* class neton.client.dns.DnsImp.RunnableC13062 */
//            {
//                synchronized(addressInfo) {
//                    val it: Iterator<*> = list.iterator()
//                    while (true) {
//                        if (!it.hasNext()) {
//                            break
//                        }
//                        val ipInfo: IpInfo? = it.next() as IpInfo?
//                        if (ipInfo != null && ipInfo.ttl > 0) {
//                            addressInfo.setTtl(ipInfo.ttl)
//                            addressInfo.setTimeStamp(ipInfo.ttl * 1000 + System.currentTimeMillis())
//                            break
//                        }
//                    }
//                    addressInfo.setIpInfoList(list)
//                    if (dnsType == DnsInfo.TYPE_HTTP) {
//                        DBUtil.addAddressInfo(mContext, addressInfo)
//                        DBUtil.deleteIpInfosByHostAndDnsType(mContext, str, dnsType)
//                        for (ipInfo2 in list) {
//                            DBUtil.addIpInfo(mContext, ipInfo2)
//                        }
//                    }
//                    addressInfo.setLooking(false)
//                }
//            })
//        }
//    }
//
//    fun delete(str: String) {
//        val addressInfo = getAddressInfo(str)
//        addressInfo.setTtl(0)
//        addressInfo.setTimeStamp(0)
//        addressInfo.setIpInfoList(null)
//        DBUtil.updateAddressInfo(mContext, addressInfo)
//        DBUtil.deleteIpInfosByHostAndDnsType(mContext, str, dnsType)
//    }

    companion object {
        const val DELETE_WEIGHT = 0.75f
        const val regex = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})"
        fun isHostIp(str: String?): Boolean {
            return Util.verifyAsIpAddress(str)
        }
    }

    init {
        mContext = context
    }
}
