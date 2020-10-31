package com.curtisy.oppounlocker.heytap.config

import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okio.Timeout
import kotlin.collections.HashMap


class NetonConfig constructor(builder: Builder) {
    var cacheDirectory: String? = null
    var dnsMode: Int
    var isCookie = true
    var isDebug = false
    var isPersistSession = true
    var isRequestCache = true
    var isStatistics = false
    var isTrace = false
    var isVerify = true
    private var liveOnHttpsMap: MutableMap<String, String>?
    var liveOnTime: Long
    var mode: Int
    private var okHttpBuilder: OkHttpClient.Builder? = null
    var regionCode: String?
    var retryIntervalTime: Long
    var retryTimes: Int
    var sessionCacheSize: Int
    var sessionTimeout: Int
    private var timeout: Timeout? = null
    var traceHit: Int

    fun okHttpBuilder(): OkHttpClient.Builder? {
        return okHttpBuilder
    }

    fun timeout(): Timeout? {
        return timeout
    }

    fun getLiveOnHttpsMap(): Map<String, String>? {
        return liveOnHttpsMap
    }

    fun newBuilder(): Builder {
        return Builder(this)
    }

    class Builder() {
        internal var cacheDirectory: String? = null
        internal var dnsMode = -1
        internal var isCookie = true
        internal var isDebug = false
        internal var isPersistSession = true
        internal var isRequestCache = false
        internal var isStatistics = false
        internal var isTrace = true
        internal var isVerify = true
        internal var liveOnHttpsMap: MutableMap<String, String>? = null
        internal var liveOnTime: Long = -1
        internal var mode = -1
        internal var okHttpBuilder: OkHttpClient.Builder? = null
        internal var regionCode: String? = null
        internal var retryIntervalTime: Long = -1
        internal var retryTimes = -1
        internal var sessionCacheSize = -1
        internal var sessionTimeout = -1
        internal var timeout: Timeout? = null
        internal var traceHit = -1

        constructor(netonConfig: NetonConfig) {
            this.isRequestCache = netonConfig.isRequestCache
            this.isVerify = netonConfig.isVerify
            this.isPersistSession = netonConfig.isPersistSession
            this.isStatistics = netonConfig.isStatistics
            this.dnsMode = netonConfig.dnsMode
            this.retryIntervalTime = netonConfig.retryIntervalTime
            this.retryTimes = netonConfig.retryTimes
            this.traceHit = netonConfig.traceHit
            this.isDebug = netonConfig.isDebug
            this.isTrace = netonConfig.isTrace
            this.isCookie = netonConfig.isCookie
            this.sessionTimeout = netonConfig.sessionTimeout
            this.sessionCacheSize = netonConfig.sessionCacheSize
            this.okHttpBuilder = netonConfig.okHttpBuilder
            this.timeout = netonConfig.timeout
            this.liveOnHttpsMap = netonConfig.liveOnHttpsMap
            this.liveOnTime = netonConfig.liveOnTime
            this.mode = netonConfig.mode
            this.regionCode = netonConfig.regionCode
        }

        @Deprecated("")
        fun verify(z: Boolean): Builder {
            return this
        }

        fun okHttpBuilder(builder: OkHttpClient.Builder?): Builder {
            this.okHttpBuilder = builder
            return this
        }

        fun timeout(timeout2: Timeout?): Builder {
            this.timeout = timeout2
            return this
        }

        fun requestCache(z: Boolean): Builder {
            this.isRequestCache = z
            return this
        }

        fun cacheDirectory(str: String?): Builder {
            this.cacheDirectory = str
            return this
        }

        fun cookie(z: Boolean): Builder {
            this.isCookie = z
            return this
        }

        fun persistSession(z: Boolean): Builder {
            this.isPersistSession = z
            return this
        }

        fun statistics(z: Boolean): Builder {
            this.isStatistics = z
            return this
        }

        fun debug(z: Boolean): Builder {
            this.isDebug = z
            return this
        }

        fun dnsMode(i: Int): Builder {
            this.dnsMode = i
            return this
        }

        fun sessionTimeout(i: Int): Builder {
            this.sessionTimeout = i
            return this
        }

        fun sessionCacheSize(i: Int): Builder {
            this.sessionCacheSize = i
            return this
        }

        fun retryTimes(i: Int): Builder {
            this.retryTimes = i
            return this
        }

        fun trace(z: Boolean): Builder {
            this.isTrace = z
            return this
        }

        fun retryIntervalTime(j: Long): Builder {
            this.retryIntervalTime = j
            return this
        }

        @Deprecated("")
        fun traceHit(i: Int): Builder {
            this.traceHit = i
            return this
        }

        fun liveOnHttpsMap(map: MutableMap<String, String>?): Builder {
            this.liveOnHttpsMap = map
            return this
        }

        private fun liveOnHttpsMap(list: List<String>?): Builder {
            if (list != null && list.isNotEmpty()) {
                this.liveOnHttpsMap = HashMap()
            }
            for (str in list!!) {
                val parse = str.toHttpUrlOrNull()
                if (parse == null) {
                    this.liveOnHttpsMap!![str] = str
                } else {
                    this.liveOnHttpsMap!![str] = parse.host
                }
            }
            return this
        }

        fun liveOnHttpsMap(vararg strArr: String): Builder {
            if (strArr.isNotEmpty()) {
                liveOnHttpsMap(strArr.toList())
            }
            return this
        }

        fun liveOnTime(j: Long): Builder {
            this.liveOnTime = j
            return this
        }

        fun mode(i: Int): Builder {
            this.mode = i
            return this
        }

        fun regionCode(str: String?): Builder {
            this.regionCode = str
            return this
        }

        fun build(): NetonConfig {
            return NetonConfig(this)
        }
    }

    companion object {
        const val DEFAULT_INT = -1
    }

    init {
        dnsMode = -1
        retryTimes = -1
        retryIntervalTime = -1
        traceHit = -1
        sessionTimeout = -1
        sessionCacheSize = -1
        liveOnHttpsMap = null
        liveOnTime = -1
        mode = -1
        regionCode = null
        isVerify = builder.isVerify
        isRequestCache = builder.isRequestCache
        cacheDirectory = builder.cacheDirectory
        isPersistSession = builder.isPersistSession
        isStatistics = builder.isStatistics
        dnsMode = builder.dnsMode
        retryTimes = builder.retryTimes
        retryIntervalTime = builder.retryIntervalTime
        traceHit = builder.traceHit
        isDebug = builder.isDebug
        isTrace = builder.isTrace
        sessionTimeout = builder.sessionTimeout
        sessionCacheSize = builder.sessionCacheSize
        okHttpBuilder = builder.okHttpBuilder
        isCookie = builder.isCookie
        timeout = builder.timeout
        liveOnHttpsMap = builder.liveOnHttpsMap
        liveOnTime = builder.liveOnTime
        mode = builder.mode
        regionCode = builder.regionCode
    }
}