package com.curtisy.oppounlocker.heytap.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.telephony.TelephonyManager
import android.text.TextUtils
import com.curtisy.oppounlocker.utilities.XORUtils
import java.io.InputStream


object NoNetworkUtil {
    const val AIRPLANE_MODE_ON_STR = 0
    val HTTPS_CAPTIVESERVER1 = XORUtils.hash("`||x{2''kgff9&kgdgzg{&kge'omfmzi|mW:8<")
    val HTTPS_CAPTIVESERVER2 = XORUtils.hash("`||x{2''kgff:&kgdgzg{&kge'omfmzi|mW:8<")
    const val MOBILE_AND_WLAN_NETWORK_NOT_CONNECT_STR = 1
    const val MOBILE_SSL_DATE_INVALID = 4
    const val NETWORK_CONNECT_OK_STR = -1
    const val NET_ERROR_DEFAULT = 6
    const val NO_NETWORK_CONNECT_STR = 3
    const val SERVER_ERROR_STR = 5
    const val SOCKET_TIMEOUT_MS = 2000
    const val TAG = "NoNetworkUtil"
    const val WLAN_NEED_LOGIN_STR = 2
    fun gatewayIp(context: Context): String {
        val long2ip = long2ip((context.getSystemService(NetHelper.CARRIER_WIFI) as WifiManager).dhcpInfo.gateway.toLong())
        return long2ip
    }

    fun getCaptivePortalStr(i: Int): Int {
        if (i == 200 || i == 204) {
            return -1
        }
        return if (i < 200 || i > 399) {
            if (i >= 500) 5 else 3
        } else 2
    }

    fun getCaptivePortalStr(context: Context?, str: String?): Int {
        var isCaptivePortal: Int
        if (isNetworkOKByURI(context, str) || isCaptivePortal(HTTPS_CAPTIVESERVER1).also { isCaptivePortal = it } == 204) {
            return -1
        }
        if (isCaptivePortal >= 200 && isCaptivePortal <= 399) {
            return 2
        }
        val isCaptivePortal2 = isCaptivePortal(HTTPS_CAPTIVESERVER2)
        if (isCaptivePortal2 == 204) {
            return -1
        }
        return if (isCaptivePortal2 < 200 || isCaptivePortal2 > 399) {
            3
        } else 2
    }

    fun getDeviceNetStatus(context: Context): Int {
        if (NetInfoHelper.m6321a(context)) {
            return -1
        }
        return if (!isAirplaneMode(context).toBoolean() || isWifiConnected(context)) {
            if (!isMobileDataConnected(context)) 1 else 3
        } else 0
    }

    fun getIpAddress(context: Context): String {
        val long2ip = long2ip((context.getSystemService(NetHelper.CARRIER_WIFI) as WifiManager).dhcpInfo.ipAddress.toLong())
        return long2ip
    }

    fun getNetStatusCode(context: Context, str: String?): Int {
        if (isAirplaneMode(context).toBoolean() && !isWifiConnected(context)) {
            return 0
        }
        if (isWifiConnected(context)) {
            return getCaptivePortalStr(context, str)
        }
        return if (hasSimCard(context)) {
            if (isMobileDataConnected(context)) -1 else 1
        } else 3
    }

    fun getNetStatusMessage(context: Context?, i: Int): String {
        return if (context == null) {
            throw RuntimeException("context is null")
        } else if (i == 0) {
            "关闭“飞行模式”或使用 WLAN 网络来访问"
        } else {
            if (1 == i) {
                return "打开“移动数据”或使用 WLAN 网络来访问"
            }
            if (2 == i) {
                return "WLAN 需认证，可点击空白处刷新"
            }
            if (3 == i) {
                return "无网络连接"
            }
            if (5 == i) {
                return "服务器正在维护，请稍后重试"
            }
            if (4 == i) "当前手机系统时间不准确，请先设置时间" else "无网络连接"
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        if (r4 != null) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0034, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        return r1.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0028, code lost:
        if (r4 != null) goto L_0x0034;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    fun getStringFromInputStream(r4: InputStream?): String {
        /*
            java.io.BufferedReader r0 = new java.io.BufferedReader
            java.io.InputStreamReader r1 = new java.io.InputStreamReader
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream
            r3.<init>(r4)
            r2.<init>(r3)
            r1.<init>(r2)
            r0.<init>(r1)
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
        L_0x0019:
            java.lang.String r2 = r0.readLine()     // Catch:{ IOException -> 0x002d }
            if (r2 == 0) goto L_0x0028
            r1.append(r2)     // Catch:{ IOException -> 0x002d }
            java.lang.String r2 = "\n"
            r1.append(r2)
            goto L_0x0019
        L_0x0028:
            if (r4 == 0) goto L_0x0037
            goto L_0x0034
        L_0x002b:
            r0 = move-exception
            goto L_0x003c
        L_0x002d:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x002b }
            if (r4 != 0) goto L_0x0034
            goto L_0x0037
        L_0x0034:
            r4.close()
        L_0x0037:
            java.lang.String r4 = r1.toString()
            return r4
        L_0x003c:
            if (r4 == 0) goto L_0x0041
            r4.close()
        L_0x0041:
            throw r0
        */
        throw UnsupportedOperationException("Method not decompiled: com.heytap.usercenter.helper.NoNetworkUtil.getStringFromInputStream(java.io.InputStream):java.lang.String")
    }

    fun getWifiSSID(context: Context): String {
        val ssid = (context.getSystemService(NetHelper.CARRIER_WIFI) as WifiManager).connectionInfo.ssid
        return if (TextUtils.isEmpty(ssid) || ssid.length <= 1 || !ssid.startsWith("\"") || !ssid.endsWith("\"")) ssid else ssid.substring(1, ssid.length - 1)
    }

    fun hasSimCard(context: Context): Boolean {
        try {
            if (isSimInserted(context, 0) || isSimInserted(context, 1)) {
                return true
            }
        } catch (unused: Exception) {
        }
        return false
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0039, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005b, code lost:
        r1 = r6;
        r6 = r0;
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0065, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0066, code lost:
        r1 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0089, code lost:
        r1.disconnect();
     */
    /* JADX WARNING: Failed to process nested try/catch */ /* JADX WARNING: Removed duplicated region for block: B:22:0x0065 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x000f] */ /* JADX WARNING: Removed duplicated region for block: B:34:0x0089  */ /* JADX WARNING: Removed duplicated region for block: B:39:0x0091  */ /* Code decompiled incorrectly, please refer to instructions dump. */
    fun isCaptivePortal(r6: String?): Int {
        /*
            r0 = 599(0x257, float:8.4E-43)
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            r2.<init>(r6)     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            java.net.URLConnection r6 = r2.openConnection()     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            r1 = 0
            r6.setInstanceFollowRedirects(r1)     // Catch:{ IOException -> 0x0068, all -> 0x0065 }
            r2 = 2000(0x7d0, float:2.803E-42)
            r6.setConnectTimeout(r2)     // Catch:{ IOException -> 0x0068, all -> 0x0065 }
            r6.setReadTimeout(r2)     // Catch:{ IOException -> 0x0068, all -> 0x0065 }
            r6.setUseCaches(r1)     // Catch:{ IOException -> 0x0068, all -> 0x0065 }
            r6.getInputStream()     // Catch:{ IOException -> 0x0068, all -> 0x0065 }
            int r1 = r6.getResponseCode()     // Catch:{ IOException -> 0x0068, all -> 0x0065 }
            r0 = 204(0xcc, float:2.86E-43)
            r2 = 200(0xc8, float:2.8E-43)
            if (r1 != r2) goto L_0x003b
            int r3 = r6.getContentLength()     // Catch:{ IOException -> 0x0039, all -> 0x0065 }
            if (r3 != 0) goto L_0x003b
            java.lang.String r3 = "NoNetworkUtil"
            java.lang.String r4 = "Empty 200 response interpreted as 204 response."
            com.platform.usercenter.common.p067b.p068a.UCLogUtil.m6302c(r3, r4)
            r1 = r0
            goto L_0x003b
        L_0x0039:
            r0 = move-exception
            goto L_0x005b
        L_0x003b:
            if (r1 != r2) goto L_0x0060
            java.lang.String r2 = "Connection"
            java.lang.String r2 = r6.getHeaderField(r2)
            if (r2 == 0) goto L_0x0060
            java.lang.String r2 = "Connection"
            java.lang.String r2 = r6.getHeaderField(r2)
            java.lang.String r3 = "Keep-Alive"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0060
            java.lang.String r2 = "NoNetworkUtil"
            java.lang.String r3 = "response 200 Connection - Alive."
            com.platform.usercenter.common.p067b.p068a.UCLogUtil.m6302c(r2, r3)
            goto L_0x0061
        L_0x005b:
            r5 = r1
            r1 = r6
            r6 = r0
            r0 = r5
            goto L_0x0071
        L_0x0060:
            r0 = r1
        L_0x0061:
            r6.disconnect()
            goto L_0x008c
        L_0x0065:
            r0 = move-exception
            r1 = r6
            goto L_0x008f
        L_0x0068:
            r1 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x0071
        L_0x006d:
            r6 = move-exception
            r0 = r6
            goto L_0x008f
        L_0x0070:
            r6 = move-exception
        L_0x0071:
            java.lang.String r2 = "NoNetworkUtil"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x008d }
            r3.<init>()     // Catch:{ all -> 0x008d }
            java.lang.String r4 = "Probably not a portal 1: exception "
            r3.append(r4)
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            com.platform.usercenter.common.p067b.p068a.UCLogUtil.m6302c(r2, r6)
            if (r1 == 0) goto L_0x008c
            r1.disconnect()
        L_0x008c:
            return r0
        L_0x008d:
            r6 = move-exception
            r0 = r6
        L_0x008f:
            if (r1 == 0) goto L_0x0094
            r1.disconnect()
        L_0x0094:
            throw r0
        */
        throw UnsupportedOperationException("Method not decompiled: com.heytap.usercenter.helper.NoNetworkUtil.isCaptivePortal(java.lang.String):int")
    }

    fun isConnectNet(context: Context): Boolean {
        return try {
            val connectivityManager = context.getSystemService("connectivity") as ConnectivityManager
            if (connectivityManager.activeNetworkInfo != null) {
                connectivityManager.activeNetworkInfo!!.isAvailable
            } else false
        } catch (e: Exception) {
            UCLogUtil.m6295a(e)
            false
        }
    }

    fun isMobileDataConnected(context: Context): Boolean {
        return if ((context.getSystemService("connectivity") as ConnectivityManager).getNetworkInfo(0)!!.state == NetworkInfo.State.CONNECTED) {
            true
        } else false
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0086, code lost:
        if (r8 != null) goto L_0x0088;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0090  */ /* Code decompiled incorrectly, please refer to instructions dump. */
    fun isNetworkOKByURI(r7: Context?, r8: String?): Boolean {
        /*
            r0 = 0
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x006e, all -> 0x006c }
            r2.<init>(r8)     // Catch:{ IOException -> 0x006e, all -> 0x006c }
            java.net.URLConnection r8 = r2.openConnection()     // Catch:{ IOException -> 0x006e, all -> 0x006c }
            java.net.HttpURLConnection r8 = (java.net.HttpURLConnection) r8     // Catch:{ IOException -> 0x006e, all -> 0x006c }
            r8.setInstanceFollowRedirects(r1)     // Catch:{ IOException -> 0x006a }
            r0 = 2000(0x7d0, float:2.803E-42)
            r8.setConnectTimeout(r0)     // Catch:{ IOException -> 0x006a }
            r8.setReadTimeout(r0)     // Catch:{ IOException -> 0x006a }
            r8.setUseCaches(r1)     // Catch:{ IOException -> 0x006a }
            int r0 = r8.getResponseCode()     // Catch:{ IOException -> 0x006a }
            java.io.InputStream r2 = r8.getInputStream()     // Catch:{ IOException -> 0x006a }
            java.lang.String r2 = getStringFromInputStream(r2)     // Catch:{ IOException -> 0x006a }
            java.lang.String r3 = gatewayIp(r7)     // Catch:{ IOException -> 0x006a }
            java.lang.String r7 = getIpAddress(r7)     // Catch:{ IOException -> 0x006a }
            java.lang.String r4 = "NoNetworkUtil"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "isNetworkOKByURI httpResponseCode ="
            r5.append(r6)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            com.platform.usercenter.common.p067b.p068a.UCLogUtil.m6297a(r4, r5)
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            r5 = 1
            if (r4 == 0) goto L_0x0050
            r8.disconnect()
            return r5
        L_0x0050:
            if (r2 == 0) goto L_0x0062
            boolean r3 = r2.contains(r3)
            if (r3 != 0) goto L_0x005e
            boolean r7 = r2.contains(r7)
            if (r7 == 0) goto L_0x0062
        L_0x005e:
            r8.disconnect()
            return r1
        L_0x0062:
            r7 = 200(0xc8, float:2.8E-43)
            if (r0 != r7) goto L_0x0088
            r8.disconnect()
            return r5
        L_0x006a:
            r7 = move-exception
            goto L_0x0070
        L_0x006c:
            r7 = move-exception
            goto L_0x008e
        L_0x006e:
            r7 = move-exception
            r8 = r0
        L_0x0070:
            java.lang.String r0 = "NoNetworkUtil"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x008c }
            r2.<init>()     // Catch:{ all -> 0x008c }
            java.lang.String r3 = "Probably not a portal: exception "
            r2.append(r3)
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            com.platform.usercenter.common.p067b.p068a.UCLogUtil.m6297a(r0, r7)
            if (r8 == 0) goto L_0x008b
        L_0x0088:
            r8.disconnect()
        L_0x008b:
            return r1
        L_0x008c:
            r7 = move-exception
            r0 = r8
        L_0x008e:
            if (r0 == 0) goto L_0x0093
            r0.disconnect()
        L_0x0093:
            throw r7
        */
        throw UnsupportedOperationException("Method not decompiled: com.heytap.usercenter.helper.NoNetworkUtil.isNetworkOKByURI(android.content.Context, java.lang.String):boolean")
    }

    fun isSimInserted(context: Context, i: Int): Boolean {
        return try {
            val telephonyManager = context.getSystemService("phone") as TelephonyManager
            val cls: Class<*> = telephonyManager.javaClass
            val method: Method = cls.getMethod("hasIccCard", Integer.TYPE)
            method.setAccessible(true)
            (method.invoke(telephonyManager, Integer.valueOf(i)) as Boolean).toBoolean()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun isWifiConnected(context: Context): Boolean {
        return if ((context.getSystemService("connectivity") as ConnectivityManager).getNetworkInfo(1)!!.state == NetworkInfo.State.CONNECTED) {
            true
        } else false
    }

    fun long2ip(j: Long): String {
        val stringBuffer = StringBuffer()
        stringBuffer.append((j and 255) as Int.toString())
        stringBuffer.append('.')
        stringBuffer.append((j shr 8 and 255) as Int.toString())
        stringBuffer.append('.')
        stringBuffer.append((j shr 16 and 255) as Int.toString())
        stringBuffer.append('.')
        stringBuffer.append((j shr 24 and 255) as Int.toString())
        return stringBuffer.toString()
    }

    fun getNetStatusCode(context: Context, i: Int): Int {
        if (isAirplaneMode(context).toBoolean() && !isWifiConnected(context)) {
            return 0
        }
        if (isWifiConnected(context)) {
            return getCaptivePortalStr(i)
        }
        return if (hasSimCard(context)) {
            if (isMobileDataConnected(context)) -1 else 1
        } else 3
    }

    fun isAirplaneMode(context: Context): Boolean {
        return try {
            var z = true
            if (Build.VERSION.SDK_INT >= 17) {
                if (Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) === 0) {
                    z = false
                }
                return java.lang.Boolean.valueOf(z)
            }
            if (Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) === 0) {
                z = false
            }
            java.lang.Boolean.valueOf(z)
        } catch (e: Exception) {
            java.lang.Boolean.FALSE
        }
    }
}