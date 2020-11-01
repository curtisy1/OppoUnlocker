package com.curtisy.oppounlocker.heytap.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.heytap.Constants
import com.curtisy.oppounlocker.heytap.manager.ConfigManager
import java.net.Inet4Address
import java.net.Inet6Address
import java.net.NetworkInterface
import java.net.SocketException


object NetHelper {
    const val CARRIER_BGP = "bgp"
    private const val CARRIER_CHINA_MOBILE = "cm"
    const val CARRIER_CHINA_TELCOM = "ct"
    private const val CARRIER_CHINA_UNION = "cu"
    private const val CARRIER_NONE = "none"
    const val CARRIER_OTHER = "ot"
    const val CARRIER_WIFI = "wifi"
    private const val NETWORK_CLASS_2_G = 1
    private const val NETWORK_CLASS_3_G = 2
    private const val NETWORK_CLASS_4_G = 3
    private const val NETWORK_CLASS_UNAVAILABLE = -1
    private const val NETWORK_CLASS_UNKNOWN = 0
    private const val NETWORK_CLASS_WIFI = -101
    private const val NETWORK_TYPE_1xRTT = 7
    private const val NETWORK_TYPE_CDMA = 4
    private const val NETWORK_TYPE_EDGE = 2
    private const val NETWORK_TYPE_EHRPD = 14
    private const val NETWORK_TYPE_EVDO_0 = 5
    private const val NETWORK_TYPE_EVDO_A = 6
    private const val NETWORK_TYPE_EVDO_B = 12
    private const val NETWORK_TYPE_GPRS = 1
    private const val NETWORK_TYPE_HSDPA = 8
    private const val NETWORK_TYPE_HSPA = 10
    private const val NETWORK_TYPE_HSPAP = 15
    private const val NETWORK_TYPE_HSUPA = 9
    private const val NETWORK_TYPE_IDEN = 11
    private const val NETWORK_TYPE_LTE = 13
    private const val NETWORK_TYPE_UMTS = 3
    private const val NETWORK_TYPE_UNAVAILABLE = -1
    private const val NETWORK_TYPE_UNKNOWN = 0
    private const val NETWORK_TYPE_WIFI = -101
    const val OPPO_CTA_USER_EXPERIENCE = "oppo_cta_user_experience"
    private const val TAG = "NetHelper"
    private var sCarrierStatus = "none"
    var lastCarrierStatus = "none"
        private set

    private fun getNetworkClassByType(i: Int): Int {
        if (i == -101) {
            return -101
        }
        if (i == -1) {
            return -1
        }
        return when (i) {
            1, 2, 4, 7, 11 -> 1
            3, 5, 6, 8, 9, 10, 12, 14, 15 -> 2
            13 -> 3
            else -> 0
        }
    }

    var carrierStatus: String
        get() = sCarrierStatus
        set(context) {
            lastCarrierStatus = sCarrierStatus
            if (isWifiConnecting(context)) {
                sCarrierStatus = CARRIER_WIFI
            } else {
                val carrierName = getCarrierName(context)
                if (CARRIER_CHINA_MOBILE == carrierName) {
                    sCarrierStatus = CARRIER_CHINA_MOBILE
                } else if (CARRIER_CHINA_UNION == carrierName) {
                    sCarrierStatus = CARRIER_CHINA_UNION
                } else if (CARRIER_CHINA_TELCOM == carrierName) {
                    sCarrierStatus = CARRIER_CHINA_TELCOM
                } else {
                    sCarrierStatus = CARRIER_NONE
                }
            }
        }

    fun isConnectNet(context: Context): Boolean {
        return try {
            val activeNetworkInfo = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
                    ?: return false
            activeNetworkInfo.isAvailable || activeNetworkInfo.isConnected
        } catch (e: Exception) {
            false
        }
    }

    fun hasAccessAndNetwork(context: Context): Boolean {
        val networkAccess = getNetworkAccess(context)
        val intData: Int = ConfigManager.getInstance().getIntData(Constants.NETTON_STATUS)
        val isConnectNet = isConnectNet(context)
        return networkAccess && intData != -1 && isConnectNet
    }

    fun isWifiConnecting(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        try {
            val state = connectivityManager.getNetworkInfo(1)!!.state
            return state != null && NetworkInfo.State.CONNECTED == state
        } catch (unused: Exception) {
        }
    }

    private fun intToIp(i: Int): String {
        return (i and 255).toString() + "." + (i shr 8 and 255) + "." + (i shr 16 and 255) + "." + (i shr 24 and 255)
    }

    val localIp6Address: String?
        get() {
            return try {
                val networkInterfaces = NetworkInterface.getNetworkInterfaces()
                while (networkInterfaces.hasMoreElements()) {
                    val inetAddresses = networkInterfaces.nextElement().inetAddresses
                    while (true) {
                        if (inetAddresses.hasMoreElements()) {
                            val nextElement = inetAddresses.nextElement()
                            if (!nextElement.isLoopbackAddress && nextElement is Inet6Address) {
                                return nextElement.getHostAddress()
                            }
                        }
                    }
                }
                null
            } catch (e: SocketException) {
                null
            }
        }

    private val localIp4Address: String
        private get() {
            return try {
                val networkInterfaces = NetworkInterface.getNetworkInterfaces()
                while (networkInterfaces.hasMoreElements()) {
                    val inetAddresses = networkInterfaces.nextElement().getInetAddresses()
                    while (true) {
                        if (inetAddresses.hasMoreElements()) {
                            val nextElement = inetAddresses.nextElement()
                            if (!nextElement.isLoopbackAddress && nextElement is Inet4Address) {
                                return nextElement.getHostAddress()
                            }
                        }
                    }
                }
                ""
            } catch (e: Exception) {
                ""
            }
        }

    fun getNetworkAccess(context: Context): Boolean {
        val i: Int
        i = try {
            Settings.System.getInt(context.contentResolver, OPPO_CTA_USER_EXPERIENCE)
        } catch (unused: Settings.SettingNotFoundException) {
            0
        }
        val sb = StringBuilder("NetHelper-getNetworkAccess val:")
        sb.append(i == 1)
        return i == 1
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0046  */ /* JADX WARNING: Removed duplicated region for block: B:25:0x0059 A[ORIG_RETURN, RETURN, SYNTHETIC] */ /* Code decompiled incorrectly, please refer to instructions dump. */
    fun getNetworkType(r4: Context?): String {
        /*
            r0 = -101(0xffffffffffffff9b, float:NaN)
            r1 = 0
            java.lang.String r2 = "connectivity"
            java.lang.Object r2 = r4.getSystemService(r2)     // Catch:{ Exception -> 0x0037 }
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2     // Catch:{ Exception -> 0x0037 }
            android.net.NetworkInfo r2 = r2.getActiveNetworkInfo()     // Catch:{ Exception -> 0x0037 }
            if (r2 == 0) goto L_0x0035
            boolean r3 = r2.isAvailable()     // Catch:{ Exception -> 0x0037 }
            if (r3 == 0) goto L_0x0035
            boolean r3 = r2.isConnected()     // Catch:{ Exception -> 0x0037 }
            if (r3 == 0) goto L_0x0035
            int r2 = r2.getType()     // Catch:{ Exception -> 0x0037 }
            r3 = 1
            if (r2 != r3) goto L_0x0026
            r4 = r0
            goto L_0x003e
        L_0x0026:
            if (r2 != 0) goto L_0x003d
            java.lang.String r2 = "phone"
            java.lang.Object r4 = r4.getSystemService(r2)     // Catch:{ Exception -> 0x0037 }
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4     // Catch:{ Exception -> 0x0037 }
            int r4 = r4.getNetworkType()     // Catch:{ Exception -> 0x0037 }
            goto L_0x003e
        L_0x0035:
            r4 = -1
            goto L_0x003e
        L_0x0037:
            r4 = move-exception
            java.lang.String r2 = neton.client.Utils.NetHelper.TAG
            neton.client.Utils.LogUtil.m6345e(r2, r4)
        L_0x003d:
            r4 = r1
        L_0x003e:
            int r4 = getNetworkClassByType(r4)
            java.lang.String r1 = "UNKNOWN"
            if (r4 == r0) goto L_0x0059
            switch(r4) {
                case -1: goto L_0x0056;
                case 0: goto L_0x0053;
                case 1: goto L_0x0050;
                case 2: goto L_0x004d;
                case 3: goto L_0x004a;
                default: goto L_0x0049;
            }
        L_0x0049:
            goto L_0x005b
        L_0x004a:
            java.lang.String r1 = "4G"
            goto L_0x005b
        L_0x004d:
            java.lang.String r1 = "3G"
            goto L_0x005b
        L_0x0050:
            java.lang.String r1 = "2G"
            goto L_0x005b
        L_0x0053:
            java.lang.String r1 = "UNKNOWN"
            goto L_0x005b
        L_0x0056:
            java.lang.String r1 = "UNKNOWN"
            goto L_0x005b
        L_0x0059:
            java.lang.String r1 = "WIFI"
        L_0x005b:
            return r1
            switch-data {-1->0x0056, 0->0x0053, 1->0x0050, 2->0x004d, 3->0x004a, }
        */
        throw UnsupportedOperationException("Method not decompiled: neton.client.Utils.NetHelper.getNetworkType(android.content.Context):java.lang.String")
    }

    fun getCarrier(context: Context): String {
        return try {
            (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).networkOperatorName
        } catch (e: Exception) {
            CARRIER_NONE
        }
    }

    fun getCarrierName(context: Context): String {
        val str: String
        var str2 = CARRIER_BGP
        try {
            val subscriberId = (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).subscriberId
            if (TextUtils.isEmpty(subscriberId)) {
                return str2
            }
            if (!subscriberId.startsWith("46000")) {
                if (!subscriberId.startsWith("46002")) {
                    if (subscriberId.startsWith("46001")) {
                        str = CARRIER_CHINA_UNION
                    } else {
                        if (subscriberId.startsWith("46003") || subscriberId.startsWith("46011") || subscriberId.startsWith("45502")) {
                            str = CARRIER_CHINA_TELCOM
                        }
                        return str2
                    }
                    str2 = str
                    return str2
                }
            }
            str = CARRIER_CHINA_MOBILE
            str2 = str
            return str2
        } catch (unused: Exception) {
        }

        return str2
    }
}
