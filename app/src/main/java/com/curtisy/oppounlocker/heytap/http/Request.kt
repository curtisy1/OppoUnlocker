package com.curtisy.oppounlocker.heytap.http

import okhttp3.CacheControl
import okhttp3.Headers
import okhttp3.HttpUrl
import okhttp3.RequestBody
import okhttp3.internal.http.HttpMethod.permitsRequestBody
import okhttp3.internal.http.HttpMethod.requiresRequestBody
import okio.Timeout
import java.net.URL


class Request private constructor(builder: Builder) {
    val body: RequestBody?

    @Volatile
    private var cacheControl: CacheControl? = null
    private val headers: Headers
    private val method: String
    private val requestStatistic: RequestStatistic?
    private val retryTimes: Int
    private val tag: Any?
    private val timeout: Timeout?
    private val url: HttpUrl?
    fun url(): HttpUrl? {
        return url
    }

    fun method(): String {
        return method
    }

    fun headers(): Headers {
        return headers
    }

    fun header(str: String?): String? {
        return headers[str!!]
    }

    fun headers(str: String?): List<String> {
        return headers.values(str!!)
    }

    fun body(): RequestBody? {
        return body
    }

    fun tag(): Any? {
        return tag
    }

    fun newBuilder(): Builder {
        return Builder()
    }

    fun timeout(): Timeout? {
        return timeout
    }

    fun retryTimes(): Int {
        return retryTimes
    }

    fun requestStatistic(): RequestStatistic? {
        return requestStatistic
    }

    fun cacheControl(): CacheControl {
        val cacheControl2 = cacheControl
        if (cacheControl2 != null) {
            return cacheControl2
        }
        val parse = CacheControl.parse(headers)
        cacheControl = parse
        return parse
    }

    val isHttps: Boolean
        get() = url!!.isHttps

    override fun toString(): String {
        val sb = StringBuilder("Request{method=")
        sb.append(method)
        sb.append(", url=")
        sb.append(url)
        sb.append(", tag=")
        sb.append(if (tag !== this) tag else null)
        sb.append('}')
        return sb.toString()
    }

    class Builder {
        private var body: RequestBody? = null
        private var headers: Headers.Builder
        private var method: String
        private var requestStatistic: RequestStatistic? = null
        private var retryTimes: Int
        private var tag: Any? = null
        private var timeout: Timeout? = null
        private var url: HttpUrl? = null

        constructor() {
            this.retryTimes = -1
            this.method = GET
            this.headers = Headers.Builder()
        }

        private constructor(request: Request) {
            this.retryTimes = -1
            this.url = request.url
            this.method = request.method
            this.body = request.body
            this.tag = request.tag
            this.headers = request.headers.newBuilder()
            this.timeout = request.timeout
            this.requestStatistic = request.requestStatistic
            this.retryTimes = request.retryTimes
        }

        fun url(httpUrl: HttpUrl?): Builder {
            if (httpUrl != null) {
                this.url = httpUrl
                return this
            }
            throw Exception(NullPointerException("url == null"))
        }

        fun url(str: String?): Builder {
            var str = str
            if (str != null) {
                if (str.regionMatches(0, "ws:", 0, 3, ignoreCase = true)) {
                    str = "http:" + str.substring(3)
                } else if (str.regionMatches(0, "wss:", 0, 4, ignoreCase = true)) {
                    str = "https:" + str.substring(4)
                }
                val parse = HttpUrl.parse(str)
                if (parse != null) {
                    return url(parse)
                }
                throw Exception(IllegalArgumentException("unexpected url: $str"))
            }
            throw Exception(NullPointerException("url == null"))
        }

        fun url(url2: URL?): Builder {
            if (url2 != null) {
                val httpUrl = HttpUrl.get(url2)
                if (httpUrl != null) {
                    return url(httpUrl)
                }
                throw Exception(IllegalArgumentException("unexpected url: $url2"))
            }
            throw Exception(NullPointerException("url == null"))
        }

        fun header(str: String?, str2: String?): Builder {
            this.headers[str!!] = str2!!
            return this
        }

        fun addHeader(str: String?, str2: String?): Builder {
            this.headers.add(str!!, str2!!)
            return this
        }

        fun removeHeader(str: String?): Builder {
            this.headers.removeAll(str!!)
            return this
        }

        fun headers(headers2: Headers): Builder {
            this.headers = headers2.newBuilder()
            return this
        }

        fun cacheControl(cacheControl: CacheControl): Builder {
            val cacheControl2 = cacheControl.toString()
            return if (cacheControl2.isEmpty()) {
                removeHeader("Cache-Control")
            } else header("Cache-Control", cacheControl2)
        }

        fun get(): Builder {
            return method(GET, null)
        }

        fun head(): Builder {
            return method(HEAD, null)
        }

        fun post(requestBody: RequestBody?): Builder {
            return method(POST, requestBody)
        }

        @JvmOverloads
        fun delete(requestBody: RequestBody? = Util.EMPTY_REQUEST): Builder {
            return method(DELETE, requestBody)
        }

        fun put(requestBody: RequestBody?): Builder {
            return method(PUT, requestBody)
        }

        fun patch(requestBody: RequestBody?): Builder {
            return method("PATCH", requestBody)
        }

        fun retryTimes(i: Int): Builder {
            this.retryTimes = i
            return this
        }

        fun method(str: String?, requestBody: RequestBody?): Builder {
            return if (str == null) {
                throw NetonException(NullPointerException("method == null"))
            } else if (str.length == 0) {
                throw NetonException(IllegalArgumentException("method.length() == 0"))
            } else if (requestBody != null && !permitsRequestBody(str)) {
                throw NetonException(IllegalArgumentException("method $str must not have a request body."))
            } else if (requestBody != null || !requiresRequestBody(str)) {
                this.method = str
                this.body = requestBody
                this
            } else {
                throw NetonException(IllegalArgumentException("method $str must have a request body."))
            }
        }

        fun tag(obj: Any?): Builder {
            this.tag = obj
            return this
        }

        fun timeout(timeout2: Timeout?): Builder {
            if (timeout2 != null) {
                this.timeout = timeout2
            }
            return this
        }

        fun requestStatistic(requestStatistic2: RequestStatistic?): Builder {
            if (requestStatistic2 != null) {
                this.requestStatistic = requestStatistic2
            }
            return this
        }

        fun build(): Request {
            if (this.url != null) {
                return Request(this)
            }
            throw NetonException(IllegalStateException("url == null"))
        }
    }

    init {
        url = builder.url
        method = builder.method
        headers = builder.headers.build()
        body = builder.body
        tag = if (builder.tag != null) builder.tag else this
        timeout = builder.timeout
        requestStatistic = builder.requestStatistic
        retryTimes = builder.retryTimes
    }
}