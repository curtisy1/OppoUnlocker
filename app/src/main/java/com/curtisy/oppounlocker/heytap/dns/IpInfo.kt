package com.curtisy.oppounlocker.heytap.dns

import android.content.Context
import android.database.Cursor
import android.text.TextUtils
import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.heytap.Constants
import com.curtisy.oppounlocker.heytap.helper.NetHelper.isConnectNet
import com.curtisy.oppounlocker.heytap.manager.ConfigManager
import com.curtisy.oppounlocker.heytap.utils.DBUtil.updateIpInfo
import com.curtisy.oppounlocker.heytap.utils.ThreadPoolUtil.execute


class IpInfo(str: String?, i: Int) : DnsInfo(str, i) {
    @get:Synchronized
    @set:Synchronized
    var failCount = 0
    var failTime: Long = 0

    /* renamed from: ip */
    var ip: String? = null
    var local: String? = null
    private var port = -1

    /* renamed from: sp */
    var sp: String = ""
    var weight = 0

    fun getPort(): Int {
        return port
    }

    fun setPort(i: Int) {
        if (i == 0) {
            port = 80
        }
        port = i
    }

    fun updateFailCount(context: Context, i: Int) {
        execute(object : Runnable {
            /* class neton.client.dns.IpInfo.RunnableC13091 */
            override fun run() {
                if (isConnectNet(context)) {
                    synchronized(this) {
                        failCount = i
                        failTime = System.currentTimeMillis()
                        updateIpInfo(context, this)
                    }
                }
            }
        })
    }

    fun checkSelect(): Boolean {
        return if (failCount != 0) {
            failCount > 0 && System.currentTimeMillis() - failTime > MAX_FAIL_TIME
        } else true
    }

    fun checkSet(): Boolean {
        val stringData = ConfigManager.getInstance().getStringData(Constants.KEY_HEADER_SET)
        return TextUtils.isEmpty(stringData) || stringData.equals(local, ignoreCase = true)
    }

    override fun toString(): String {
        return this.host.toString() + ",dnsType:" + this.dnsType + ",ip:" + ip + "," + port + "," + weight + "," + sp + "," + local + "," + failCount + "," + failTime
    }

    val isIpInfoValid: Boolean
        get() = !TextUtils.isEmpty(ip)

    companion object {
        const val ERROR_PORT = -1
        const val FAIL_COUNT = "failCount"
        const val FAIL_TIME = "failTime"

        /* renamed from: IP */
        const val f7044IP = "ip"
        const val LOCAL = "local"
        const val MAX_FAIL_TIME: Long = 1800000
        const val PORT = "port"

        /* renamed from: SP */
        const val f7045SP = "sp"
        const val WEIGHT = "weight"
        fun parseIpInfoList(str: String?, i: Int, list: List<String>?): ArrayList<IpInfo>? {
            if (list == null) {
                return null
            }
            val arrayList = ArrayList<IpInfo>()
            for (i2 in list.indices) {
                val parseIpInfo = parseIpInfo(str, i, list[i2])
                if (parseIpInfo != null) {
                    arrayList.add(parseIpInfo)
                }
            }
            return arrayList
        }

        fun parseIpInfoByIp(str: String?, i: Int, str2: String): IpInfo? {
            if (TextUtils.isEmpty(str2)) {
                return null
            }
            val ipInfo = IpInfo(str, i)
            ipInfo.ip = str2
            return ipInfo
        }

        fun parseIpInfo(str: String?, i: Int, str2: String): IpInfo? {
            if (TextUtils.isEmpty(str2)) {
                return null
            }
            val split = str2.trim { it <= ' ' }.split(",").toTypedArray()
            return if (split.size >= 5 || split.size <= 0) {
                try {
                    val ipInfo = IpInfo(str, i)
                    ipInfo.ip = split[0]
                    ipInfo.setPort(split[1].toInt())
                    ipInfo.ttl = split[2].toLong()
                    ipInfo.weight = split[3].toInt()
                    ipInfo.sp = split[4]
                    ipInfo.local = if (split.size > 5) split[5] else ""
                    ipInfo
                } catch (e: Exception) {
                    null
                }
            } else {
                parseIpInfoByIp(str, i, split[0])
            }
        }

        fun getIpInfoByCursor(str: String?, i: Int, cursor: Cursor?): IpInfo? {
            if (cursor == null || cursor.isClosed) {
                return null
            }
            val ipInfo = IpInfo(str, i)
            ipInfo.ip = cursor.getString(cursor.getColumnIndex("ip"))
            ipInfo.setPort(cursor.getInt(cursor.getColumnIndex(PORT)))
            ipInfo.ttl = cursor.getLong(cursor.getColumnIndex(DnsInfo.TTL))
            ipInfo.weight = cursor.getInt(cursor.getColumnIndex(WEIGHT))
            ipInfo.local = cursor.getString(cursor.getColumnIndex(LOCAL))
            ipInfo.sp = cursor.getString(cursor.getColumnIndex(f7045SP))
            ipInfo.failCount = cursor.getInt(cursor.getColumnIndex(FAIL_COUNT))
            ipInfo.failTime = cursor.getLong(cursor.getColumnIndex(FAIL_TIME))
            return ipInfo
        }
    }
}
