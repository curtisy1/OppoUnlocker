package com.curtisy.oppounlocker.heytap.account

import android.text.TextUtils
import com.curtisy.oppounlocker.heytap.usercenter.UCCommonResponse
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.zip.GZIPInputStream


class AccountNameProtocol : UCCommonResponse<JSONObject>() {
    override fun parserData(jSONObject: JSONObject): JSONObject {
        return jSONObject
    }

    companion object {
        /* renamed from: a */
        fun m6217a(str: String) {
        }

        /* renamed from: b */
        fun m6219b(str: String): String {
            if ("1000" == str) {
                return "获取网络数据成功"
            }
            if ("2000" == str) {
                return "获取缓存数据成功"
            }
            if ("1001" == str) {
                return "账号未登录"
            }
            if ("1002" == str) {
                return "账号登录失败"
            }
            if ("1003" == str) {
                return "操作失败"
            }
            if ("2001" == str) {
                return "网络异常"
            }
            if ("3040" == str) {
                return "登录状态已失效"
            }
            return if ("3013" == str) "账户异常" else "操作失败"
        }

        /* renamed from: a */
        fun m6218a(httpURLConnection: HttpURLConnection): ByteArray? {
            val inputStream: InputStream?
            httpURLConnection.connect()
            var bArr: ByteArray? = null
            if (httpURLConnection.responseCode !== 200) {
                m6217a("HTTP code: " + httpURLConnection.responseCode.toString() + ", url = " + httpURLConnection.url)
                return null
            }
            val contentEncoding: String = httpURLConnection.contentEncoding
            if (!contentEncoding.contains("gzip")) {
                inputStream = httpURLConnection.inputStream
            } else {
                inputStream = GZIPInputStream(httpURLConnection.inputStream)
            }
            if (inputStream != null) {
                bArr = try {
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    val bArr2 = ByteArray(4096)
                    while (true) {
                        val read: Int = inputStream.read(bArr2, 0, 4096)
                        if (read <= 0) {
                            break
                        }
                        byteArrayOutputStream.write(bArr2, 0, read)
                    }
                    byteArrayOutputStream.toByteArray()
                } catch (th: Throwable) {
                    if (inputStream != null) {
                        try {
                            inputStream.close()
                        } catch (unused: IOException) {
                        }
                    }
                    throw th
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (unused2: IOException) {
                }
            }
            return bArr
        }

        /* renamed from: b */
        fun m6220b(str: String?, str2: String?, map: Map<String?, String?>?): ByteArray? {
            var a: HttpURLConnection
            if (TextUtils.isEmpty(str) || m6216a("POST", str, map).also { a = it } == null) {
                return null
            }
            a.useCaches = false
            a.doInput = true
            a.doOutput = true
            val printWriter = PrintWriter(a.outputStream)
            if (!TextUtils.isEmpty(str2)) {
                printWriter.print(str2)
            }
            printWriter.flush()
            printWriter.close()
            return m6218a(a)
        }

        /* renamed from: a */
        fun m6216a(str: String?, str2: String?, map: Map<String?, String?>?): HttpURLConnection? {
            return try {
                val httpURLConnection = URL(str2).openConnection() as HttpURLConnection
                val sb = StringBuilder()
                sb.append("request url: ")
                sb.append(str2)
                m6217a(sb.toString())
                httpURLConnection.setRequestProperty("Accept", "*/*")
                httpURLConnection.setRequestProperty("Accept-Language", "zh-cn")
                httpURLConnection.setRequestProperty("Accept-Charset", "utf-8")
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive")
                if (map != null) {
                    for (str3 in map.keys) {
                        httpURLConnection.setRequestProperty(str3, map[str3])
                    }
                }
                httpURLConnection.connectTimeout = 10000
                httpURLConnection.readTimeout = 30000
                httpURLConnection.requestMethod = str
                httpURLConnection
            } catch (e: MalformedURLException) {
                m6217a("error: " + e.message)
                null
            } catch (e2: IOException) {
                m6217a("error: " + e2.message)
                null
            } catch (e3: Exception) {
                m6217a("error: " + e3.message)
                null
            }
        }
    }
}