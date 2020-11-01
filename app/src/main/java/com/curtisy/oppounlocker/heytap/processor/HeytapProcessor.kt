package com.curtisy.oppounlocker.heytap.processor

import android.content.Context
import android.net.SSLSessionCache
import android.text.TextUtils
import android.util.Log
import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.heytap.Constants
import com.curtisy.oppounlocker.heytap.config.NetonConfig
import com.curtisy.oppounlocker.heytap.dns.DnsImp
import com.curtisy.oppounlocker.heytap.dns.IpInfo
import com.curtisy.oppounlocker.heytap.helper.NetHelper.getNetworkType
import com.curtisy.oppounlocker.heytap.manager.ConfigManager
import com.curtisy.oppounlocker.heytap.manager.DnsManager
import com.curtisy.oppounlocker.heytap.utils.ThreadPoolUtil
import com.curtisy.oppounlocker.heytap.utils.ThreadPoolUtil.execute
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.File
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLException
import javax.net.ssl.X509TrustManager


class HeytapProcessor(context: Context?, netonConfig: NetonConfig?) {
    var mContext: Context?
    private var mFile: File? = null
    var netonConfig: NetonConfig?
    var okHttpClient: OkHttpClient?
        private set

    fun checkRegionCode() {
        if (1 != ConfigManager.instance.getIntData(Constants.KEY_DNS_MODE, 1) && TextUtils.isEmpty(ConfigManager.instance.getStringData(Constants.KEY_REGIONCODE, ""))) {
            throw Exception("fatal error, exception, please set regionCode when use httpDns !")
        }
    }

    fun process(request: Request, z: Boolean): Response {
        return if (z) {
            okHttpClient!!.newCall(request).execute()
        } else process(request)
    }

    fun process(request: Request, callback: Callback, z: Boolean) {
        if (z) {
            okHttpClient!!.newCall(request).enqueue(object : Callback() {
                /* class com.coloros.neton.Processor.C12401 */
                // neton.Callback
                fun onFailure(call: Call?, iOException: IOException?) {
                    callback.onFailure(call, iOException)
                }

                // neton.Callback
                fun onResponse(call: Call?, response: Response?) {
                    callback.onResponse(call, response)
                }
            })
        } else {
            process(request, callback)
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0063  */ /* JADX WARNING: Removed duplicated region for block: B:46:0x00f2  */ /* JADX WARNING: Removed duplicated region for block: B:55:0x0119 A[SYNTHETIC, Splitter:B:55:0x0119] */ /* JADX WARNING: Removed duplicated region for block: B:61:0x013a A[RETURN] */ /* JADX WARNING: Removed duplicated region for block: B:62:0x013b  */ /* JADX WARNING: Removed duplicated region for block: B:65:0x0138 A[EDGE_INSN: B:65:0x0138->B:60:0x0138 ?: BREAK  , SYNTHETIC] */ /* JADX WARNING: Removed duplicated region for block: B:68:0x0134 A[SYNTHETIC] */ /* JADX WARNING: Removed duplicated region for block: B:9:0x0053  */ /* Code decompiled incorrectly, please refer to instructions dump. */
    fun process(r17: neton.Request?): neton.Response {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            neton.HttpUrl r0 = r17.url()
            java.lang.String r0 = r0.host()
            boolean r3 = neton.client.dns.DnsImp.isHostIp(r0)
            java.lang.String r0 = ""
            java.lang.String r4 = "Processor--process-isHostIp:"
            java.lang.String r5 = java.lang.String.valueOf(r3)
            java.lang.String r4 = r4.concat(r5)
            neton.client.Utils.LogUtil.m6340d(r4)
            int r4 = r17.retryTimes()
            r5 = 0
            r6 = 1
            if (r4 >= 0) goto L_0x0036
            if (r3 == 0) goto L_0x002b
        L_0x0029:
            r4 = r5
            goto L_0x003d
        L_0x002b:
            neton.client.config.ConfigManager r4 = neton.client.config.ConfigManager.instance
            java.lang.String r7 = "retry_times"
            int r4 = r4.getIntData(r7, r6)
            goto L_0x003d
        L_0x0036:
            if (r3 == 0) goto L_0x0039
            goto L_0x0029
        L_0x0039:
            int r4 = r17.retryTimes()
        L_0x003d:
            neton.client.config.ConfigManager r7 = neton.client.config.ConfigManager.instance
            java.lang.String r8 = "retry_interval_time"
            r9 = 0
            java.lang.Long r11 = java.lang.Long.valueOf(r9)
            java.lang.Long r7 = r7.getLongData(r8, r11)
            long r7 = r7.longValue()
            if (r4 > 0) goto L_0x005c
            boolean r12 = r16.isHttpsLiveOn(r17)
            if (r12 == 0) goto L_0x005c
            r13 = r0
            r4 = r6
            goto L_0x005d
        L_0x005c:
            r13 = r0
        L_0x005d:
            r0 = 0
            r12 = 0
        L_0x005f:
            int r14 = r4 + -1
            if (r4 < 0) goto L_0x0138
            neton.client.statistics.RequestStatistic r0 = new neton.client.statistics.RequestStatistic
            android.content.Context r4 = r1.mContext
            java.lang.String r4 = neton.client.Utils.Util.getTraceID(r4)
            r0.<init>(r4)
            neton.Request r4 = r16.getLiveOnRequest(r17)
            if (r3 == 0) goto L_0x0076
            r15 = 0
            goto L_0x007a
        L_0x0076:
            neton.client.dns.IpInfo r15 = r1.getIpInfo(r4, r0)
        L_0x007a:
            neton.Request r4 = r1.getProcessRequest(r4, r0, r15)
            neton.OkHttpClient r0 = r1.mOkHttpClient     // Catch:{ IOException -> 0x00ee }
            neton.Call r0 = r0.newCall(r4)     // Catch:{ IOException -> 0x00ee }
            neton.Response r11 = r0.execute()     // Catch:{ IOException -> 0x00ee }
            boolean r0 = neton.client.Utils.LogUtil.isDebugs()     // Catch:{ IOException -> 0x00eb }
            if (r0 == 0) goto L_0x00af
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00eb }
            java.lang.String r12 = "Processor--process--retryCount:"
            r0.<init>(r12)     // Catch:{ IOException -> 0x00eb }
            r0.append(r14)     // Catch:{ IOException -> 0x00eb }
            java.lang.String r12 = "request:"
            r0.append(r12)     // Catch:{ IOException -> 0x00eb }
            r0.append(r4)     // Catch:{ IOException -> 0x00eb }
            java.lang.String r12 = ",response:"
            r0.append(r12)     // Catch:{ IOException -> 0x00eb }
            r0.append(r11)     // Catch:{ IOException -> 0x00eb }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x00eb }
            neton.client.Utils.LogUtil.m6340d(r0)     // Catch:{ IOException -> 0x00eb }
        L_0x00af:
            int r0 = r11.code()     // Catch:{ IOException -> 0x00eb }
            r12 = 200(0xc8, float:2.8E-43)
            if (r0 == r12) goto L_0x00bb
            java.lang.String r13 = r11.message()     // Catch:{ IOException -> 0x00eb }
        L_0x00bb:
            boolean r12 = r11.isSuccessful()     // Catch:{ IOException -> 0x00eb }
            if (r12 == 0) goto L_0x00d3
            r1.statisticRequest(r4, r0, r13)     // Catch:{ IOException -> 0x00eb }
            neton.client.dns.DnsManager r0 = neton.client.dns.DnsManager.instance     // Catch:{ IOException -> 0x00eb }
            r0.updateIpInfo(r15)     // Catch:{ IOException -> 0x00eb }
            neton.client.liveon.LiveOnHttpsManager r0 = neton.client.liveon.LiveOnHttpsManager.instance     // Catch:{ IOException -> 0x00eb }
            r0.updateLiveOn(r2, r4, r6)     // Catch:{ IOException -> 0x00eb }
            return r11
        L_0x00d3:
            if (r15 == 0) goto L_0x00da
            android.content.Context r12 = r1.mContext     // Catch:{ IOException -> 0x00eb }
            r15.updateFailCount(r12, r6)     // Catch:{ IOException -> 0x00eb }
        L_0x00da:
            int r12 = r11.code()     // Catch:{ IOException -> 0x00eb }
            r9 = 408(0x198, float:5.72E-43)
            if (r12 == r9) goto L_0x00e8
            r1.statisticRequest(r4, r0, r13)     // Catch:{ IOException -> 0x00eb }
            r12 = r11
            r0 = 0
            goto L_0x0138
        L_0x00e8:
            r12 = r11
            r11 = 0
            goto L_0x0110
        L_0x00eb:
            r0 = move-exception
            r12 = r11
            goto L_0x00ef
        L_0x00ee:
            r0 = move-exception
        L_0x00ef:
            r11 = r0
            if (r15 == 0) goto L_0x00f7
            android.content.Context r0 = r1.mContext
            r15.updateFailCount(r0, r6)
        L_0x00f7:
            r0 = 600(0x258, float:8.41E-43)
            java.lang.String r9 = r11.toString()
            boolean r10 = r11 instanceof java.net.ConnectException
            if (r10 != 0) goto L_0x0108
            boolean r10 = r11 instanceof javax.net.ssl.SSLException
            if (r10 == 0) goto L_0x0106
            goto L_0x0108
        L_0x0106:
            r0 = r11
            goto L_0x0138
        L_0x0108:
            neton.client.liveon.LiveOnHttpsManager r10 = neton.client.liveon.LiveOnHttpsManager.instance
            r10.updateLiveOn(r2, r4, r5)
            r13 = r9
        L_0x0110:
            r1.statisticRequest(r4, r0, r13)
            r9 = 0
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x0134
            java.lang.Thread.sleep(r7)     // Catch:{ InterruptedException -> 0x011d }
            goto L_0x0134
        L_0x011d:
            r0 = move-exception
            r4 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r15 = "Processor--process--sleep:"
            r0.<init>(r15)
            java.lang.String r4 = r4.toString()
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            neton.client.Utils.LogUtil.m6340d(r0)
        L_0x0134:
            r0 = r11
            r4 = r14
            goto L_0x005f
        L_0x0138:
            if (r0 != 0) goto L_0x013b
            return r12
        L_0x013b:
            throw r0
        */
        throw UnsupportedOperationException("Method not decompiled: com.coloros.neton.Processor.process(neton.Request):neton.Response")
    }

    fun process(request: Request, callback: Callback) {
        val isHostIp = DnsImp.isHostIp(request.url().host())
        val retry = Retry()
        var i = 0
        if (request.retryTimes() < 0) {
            if (!isHostIp) {
                i = ConfigManager.instance.getIntData(Constants.KEY_RETRY_TIMES, 1)
            }
            retry.retryCount = i
        } else {
            if (!isHostIp) {
                i = request.retryTimes()
            }
            retry.retryCount = i
        }
        retry.retryTime = ConfigManager.instance.getLongData(Constants.KEY_RETRY_INTERVAL_TIME, 0L).longValue()
        if (retry.retryCount <= 0 && isHttpsLiveOn(request)) {
            retry.retryCount = 1
        }
        process(isHostIp, request, callback, object : Callback() {
            /* class com.coloros.neton.Processor.C12412 */
            // neton.Callback
            fun onFailure(call: Call?, iOException: IOException?) {
                retry.retryCount = retry.retryCount - 1
                if (retry.retryCount < 0 || iOException !is ConnectException && iOException !is SSLException) {
                    callback.onFailure(call, iOException)
                    return
                }
                if (retry.retryTime != 0L) {
                    try {
                        Thread.sleep(retry.retryTime)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                try {
                    this@Processor.process(isHostIp, request, callback, this)
                } catch (e2: NetonException) {
                    e2.printStackTrace()
                }
            }

            // neton.Callback
            fun onResponse(call: Call?, response: Response) {
                retry.retryCount = retry.retryCount - 1
                if (retry.retryCount < 0 || response.code != 408) {
                    retry.retryCount = 0
                    callback.onResponse(call, response)
                    return
                }
                if (retry.retryTime != 0L) {
                    try {
                        Thread.sleep(retry.retryTime)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                this@Processor.process(isHostIp, request, callback, this)
            }
        })
    }

    /* access modifiers changed from: private */ /* access modifiers changed from: public */
    private fun process(z: Boolean, request: Request, callback: Callback, callback2: Callback) {
        val requestStatistic = RequestStatistic(Util.getTraceID(mContext))
        val liveOnRequest: Request = getLiveOnRequest(request)
        var ipInfo: IpInfo? = null
        if (!z) {
            try {
                ipInfo = getIpInfo(liveOnRequest, requestStatistic)
            } catch (e: UnknownHostException) {
                e.printStackTrace()
            }
        }
        val processRequest: Request = getProcessRequest(liveOnRequest, requestStatistic, ipInfo)
        okHttpClient!!.newCall(processRequest).enqueue(object : Callback() {
            /* class com.coloros.neton.Processor.C12423 */
            // neton.Callback
            fun onFailure(call: Call?, iOException: IOException) {
                ipInfo?.updateFailCount(mContext!!, 1)
                callback2.onFailure(call, iOException)
                statisticRequest(processRequest, ResponseCode.CODE_6XX_CONNECT_ERROR, iOException.toString())
                if (iOException is ConnectException || iOException is SSLException) {
                    LiveOnHttpsManager.instance.updateLiveOn(request, processRequest, false)
                }
            }

            // neton.Callback
            fun onResponse(call: Call?, response: Response) {
                if (response.isSuccessful) {
                    callback.onResponse(call, response)
                    statisticRequest(processRequest, response.code, if (response.code == 200) "" else response.message)
                    DnsManager.instance.updateIpInfo(ipInfo)
                    LiveOnHttpsManager.instance.updateLiveOn(request, processRequest, true)
                    return
                }
                ipInfo?.updateFailCount(mContext!!, 1)
                callback2.onResponse(call, response)
                statisticRequest(processRequest, response.code, if (response.code == 200) "" else response.message)
            }
        })
    }

    /* access modifiers changed from: package-private */
    inner class Retry private constructor() {
        @get:Synchronized
        @set:Synchronized
        var retryCount = 0
        var retryTime: Long = 0
    }

    private fun getIpInfo(request: Request, requestStatistic: RequestStatistic): IpInfo? {
        var str: String?
        requestStatistic.setDnsStartTime(System.currentTimeMillis())
        var ipInfo: IpInfo? = null
        try {
            val lookup: IpInfo = DnsManager.instance.lookup(request.url().host())
            str = ""
            e = null
            ipInfo = lookup
        } catch (e: UnknownHostException) {
            e = e
            LogUtil.m6340d("Processor--process--UnknownHostException:$e")
            str = e.toString()
        }
        requestStatistic.setDnsEndTime(System.currentTimeMillis())
        if (ipInfo == null || e != null) {
            statisticRequest(request, ResponseCode.CODE_6XX_DNS_ERROR, str)
            if (e != null) {
                throw e
            }
        }
        return ipInfo
    }

    fun statisticRequest(request: Request?, i: Int, str: String?) {
        if (netonConfig == null || netonConfig!!.isStatistics || netonConfig!!.isTrace || request.requestStatistic() == null || TextUtils.isEmpty(request.requestStatistic().getTraceID())) {
            execute {
                val requestStatistic: RequestStatistic = request.requestStatistic()
                if (request != null && requestStatistic != null) {
                    requestStatistic.setIp(request.url().mo7289ip())
                    requestStatistic.setNetworkType(getNetworkType(mContext))
                    requestStatistic.setUrl(request.url().toString())
                    requestStatistic.setEndTime(System.currentTimeMillis())
                    requestStatistic.setResponseCode(i)
                    requestStatistic.setResponseMessage(str)
                    val httpUrl: String = request.url().toString()
                    val indexOf = httpUrl.indexOf("?")
                    if (indexOf != -1) {
                        requestStatistic.setServiceMethod(request.method().toString() + " " + httpUrl.substring(0, indexOf))
                    } else {
                        requestStatistic.setServiceMethod(request.method().toString() + " " + httpUrl)
                    }
                    LogUtil.m6340d("statisticRequest--" + requestStatistic.toString())
                    if (netonConfig != null && netonConfig!!.isStatistics) {
                        StatisticUtil.statisticMessage(mContext, requestStatistic)
                    }
                    if (netonConfig != null && netonConfig!!.isTrace) {
                        StatisticUtil.statisticTrace(mContext, requestStatistic)
                    }
                }
            }
        } else {
            LogUtil.m6340d("statisticRequest--no trace,no statistic")
        }
    }

    private fun getProcessRequest(request: Request, requestStatistic: RequestStatistic, ipInfo: IpInfo?): Request {
        LogUtil.m6340d("Processor--getProcessRequest--start:" + ipInfo.toString())
        return try {
            val newBuilder: HttpUrl.Builder = request.url().newBuilder()
            if (ipInfo != null) {
                newBuilder.mo7327ip(ipInfo.ip)
                if (ipInfo.getPort() != -1) {
                    newBuilder.port(ipInfo.getPort())
                }
            } else {
                newBuilder.mo7327ip(request.url().host())
            }
            val newBuilder2: Request.Builder = request.newBuilder()
            val instance: ConfigManager = ConfigManager.instance
            val str: String = Constants.DEFAULT_ENCYPT_VERSION
            newBuilder2.header(ENCYPT_HEADER, instance.getStringData(str, str))
            newBuilder2.header(NETON_HEADER, Util.getNetonHeader(mContext))
            if (!TextUtils.isEmpty(requestStatistic.getTraceID())) {
                newBuilder2.header(TRACE_ID, requestStatistic.getTraceID())
                newBuilder2.header(LEVEL, Constants.TRACE_LEVEL_12)
            }
            newBuilder2.requestStatistic(requestStatistic)
            newBuilder2.url(newBuilder.build())
            newBuilder2.build()
        } catch (e: NetonException) {
            LogUtil.m6340d("getProcessRequest--NetonException:" + e.toString())
            request
        } catch (e2: Exception) {
            LogUtil.m6340d("getProcessRequest--Exception:$e2")
            request
        }
    }

    fun getLiveOnRequest(request: Request): Request {
        try {
            if (request.url().isHttps()) {
                val liveOnHttps: LiveOnHttps = LiveOnHttpsManager.instance.getLiveOnHttps(request.url())
                LogUtil.m6340d("getLiveOnRequest--liveOnHttps:" + java.lang.String.valueOf(liveOnHttps))
                if (liveOnHttps != null && !liveOnHttps.isHttpsLiveOn()) {
                    val newBuilder: HttpUrl.Builder = request.url().newBuilder()
                    newBuilder.scheme("http")
                    newBuilder.host(liveOnHttps.getHttpUrl().host())
                    newBuilder.port(liveOnHttps.getHttpUrl().port())
                    val newBuilder2: Request.Builder = request.newBuilder()
                    newBuilder2.url(newBuilder.build())
                    return newBuilder2.build()
                }
            }
        } catch (e: NetonException) {
            LogUtil.m6340d("getLiveOnRequest--NetonException:" + e.toString())
        } catch (e2: Exception) {
            LogUtil.m6340d("getLiveOnRequest--Exception:$e2")
        }
        return request
    }

    fun isHttpsLiveOn(request: Request): Boolean {
        return request.url().isHttps() && LiveOnHttpsManager.instance.getLiveOnHttps(request.url()) != null
    }

    fun cancel(obj: Any) {
        for (call in okHttpClient!!.dispatcher.queuedCalls()) {
            if (obj == call.request().tag()) {
                call.cancel()
            }
        }
        for (call2 in okHttpClient!!.dispatcher.runningCalls()) {
            if (obj == call2.request().tag()) {
                call2.cancel()
            }
        }
    }

    fun close() {
        try {
            okHttpClient!!.dispatcher.cancelAll()
            okHttpClient!!.dispatcher.executorService.shutdown()
            okHttpClient!!.connectionPool.evictAll()
            okHttpClient!!.cache!!.close()
            okHttpClient = null
            ThreadPoolUtil.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val ENCYPT_HEADER = "encypt"
        private const val LEVEL = "level"
        private const val NETON_HEADER = "O_NETON"
        private const val TRACE_ID = "traceId"
    }

    init {
        var sSLSessionCache: SSLSessionCache? = null
        mContext = context
        this.netonConfig = netonConfig
        if (this.netonConfig == null) {
            this.netonConfig = NetonConfig.Builder().build()
        }
        if (-1L != this.netonConfig!!.retryIntervalTime) {
            ConfigManager.instance.setLongData(Constants.KEY_RETRY_INTERVAL_TIME, java.lang.Long.valueOf(this.netonConfig!!.retryIntervalTime))
        }
        if (-1 != this.netonConfig!!.retryTimes) {
            ConfigManager.instance.setIntData(Constants.KEY_RETRY_TIMES, this.netonConfig!!.retryTimes)
        }
        this.netonConfig!!.traceHit
        if (-1 != this.netonConfig!!.dnsMode) {
            ConfigManager.instance.setIntData(Constants.KEY_DNS_MODE, this.netonConfig!!.dnsMode)
        }
        if (-1 != this.netonConfig!!.sessionTimeout) {
            ConfigManager.instance.setIntData(Constants.KEY_SESSION_TIMEOUT, this.netonConfig!!.sessionTimeout)
        }
        if (-1 != this.netonConfig!!.sessionCacheSize) {
            ConfigManager.instance.setIntData(Constants.KEY_SESSION_CACHE_SIZE, this.netonConfig!!.sessionCacheSize)
        }
        if (-1L != this.netonConfig!!.liveOnTime) {
            ConfigManager.instance.setLongData(Constants.KEY_LIVE_ON_TIME, java.lang.Long.valueOf(this.netonConfig!!.liveOnTime))
        }
        if (this.netonConfig!!.getLiveOnHttpsMap() != null && !this.netonConfig!!.getLiveOnHttpsMap()!!.isEmpty()) {
            LiveOnHttpsManager.instance.parseLiveOnHttpsMap(this.netonConfig!!.getLiveOnHttpsMap())
        }
        if (-1 != this.netonConfig!!.mode) {
            ConfigManager.instance.setIntData(Constants.KEY_MODE, this.netonConfig!!.mode)
            ModeUtil.setMode(this.netonConfig!!.mode)
        }
        if (this.netonConfig!!.regionCode != null) {
            ConfigManager.instance.setStringData(Constants.KEY_REGIONCODE, this.netonConfig!!.regionCode)
        }
        var okHttpBuilder = this.netonConfig!!.okHttpBuilder()
        okHttpBuilder = okHttpBuilder ?: OkHttpClient.Builder()
        if (this.netonConfig!!.timeout() != null) {
            okHttpBuilder.timeout(this.netonConfig!!.timeout())
        }
        okHttpBuilder.addInterceptor(OppoSetInterceptor())
        if (this.netonConfig!!.isDebug) {
            LogUtil.setDebugs(true)
            okHttpBuilder.addInterceptor(LoggingInterceptor())
            okHttpBuilder.addNetworkInterceptor(NetworkInterceptor())
        }
        if (this.netonConfig!!.isRequestCache) {
            try {
                if (!TextUtils.isEmpty(this.netonConfig!!.cacheDirectory)) {
                    mFile = File(this.netonConfig!!.cacheDirectory)
                }
                if (mFile == null || !mFile.exists()) {
                    mFile = mContext!!.cacheDir
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if (mFile != null && mFile.exists()) {
                okHttpBuilder.cache(Cache(mFile, 1010241024))
            }
        }
        if (this.netonConfig!!.isCookie) {
            okHttpBuilder.cookieJar(CookiesManager(context))
        }
        if (!this.netonConfig!!.isVerify) {
            okHttpBuilder.sslSocketFactory(TrustAllCertification.createSSLSocketFactory())
            okHttpBuilder.hostnameVerifier(TrustAllHostnameVerifier())
        } else {
            if (this.netonConfig!!.isPersistSession && context != null) {
                sSLSessionCache = SSLSessionCache(context)
            }
            try {
                val systemDefaultTrustManager: X509TrustManager = NetonSslSocketFactory.systemDefaultTrustManager()
                okHttpBuilder.sslSocketFactory(NetonSslSocketFactory.createNetonSslSocketFactory(systemDefaultTrustManager, sSLSessionCache), systemDefaultTrustManager)
            } catch (e2: KeyManagementException) {
                e2.printStackTrace()
            } catch (e3: NoSuchAlgorithmException) {
                e3.printStackTrace()
            } catch (e4: Exception) {
                e4.printStackTrace()
            }
        }
        okHttpClient = okHttpBuilder.build()
        checkRegionCode()
        Util.getNetonHeader(mContext)
    }
}
