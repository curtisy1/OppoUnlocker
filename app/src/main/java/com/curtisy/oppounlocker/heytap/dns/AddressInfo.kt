package com.curtisy.oppounlocker.heytap.dns

import android.database.Cursor

class AddressInfo : DnsInfo {
    private var infos: List<IpInfo>? = null
    private var ipInfo: IpInfo? = null
    var isLooking = false
    var timeStamp: Long = 0

    constructor(str: String?, i: Int) : super(str, i) {}
    constructor() {}

    @Synchronized
    fun getIpInfo(): IpInfo? {
        return ipInfo
    }

    @Synchronized
    fun setIpInfo(ipInfo2: IpInfo?) {
        ipInfo = ipInfo2
    }

    var ipInfoList: List<IpInfo>?
        get() = infos
        set(list) {
            infos = list
        }

    override fun toString(): String {
        return "host:" + host + ",dsnType:" + this.dnsType + ",ttl:" + ttl + ",timeStamp:" + timeStamp + ",list:" + infos + ",ipInfo:" + ipInfo
    }

    companion object {
        const val TIMESTAMP = "timeStamp"
        fun getAddressInfoByCursor(cursor: Cursor?): AddressInfo? {
            if (cursor == null || cursor.isClosed) {
                return null
            }
            val addressInfo = AddressInfo()
            addressInfo.host = cursor.getString(cursor.getColumnIndex(HOST))
            addressInfo.dnsType = cursor.getInt(cursor.getColumnIndex(DNS_TYPE))
            addressInfo.ttl = cursor.getLong(cursor.getColumnIndex(TTL))
            addressInfo.timeStamp = cursor.getLong(cursor.getColumnIndex(TIMESTAMP))
            return addressInfo
        }
    }
}
