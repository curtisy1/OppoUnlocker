package com.curtisy.oppounlocker.heytap

import android.content.Context
import android.content.Intent
import com.curtisy.oppounlocker.heytap.config.NetonConfig
import com.curtisy.oppounlocker.heytap.manager.ConfigManager
import com.curtisy.oppounlocker.heytap.manager.DnsManager
import com.curtisy.oppounlocker.heytap.processor.HeytapProcessor
import okio.Timeout
import java.util.concurrent.Flow


class NetonClient private constructor() {
    private var mContext: Context? = null

    val context: Context?
        get() = mContext

    @Synchronized
    fun init(context: Context) {
        init(context, null)
    }

    @Synchronized
    fun init(context: Context, netonConfig: NetonConfig?) {
        if (mContext == null) {
            try {
                mContext = context.applicationContext
                ConfigManager.instance.init(mContext!!)
                DnsManager.instance.init(mContext!!)
                sMProcessor = HeytapProcessor(mContext, netonConfig)
            } catch (th: Throwable) {
                throw Exception(th)
            }
        }
    }

    fun cancel(obj: Any?) {
        checkInit()
        sMProcessor.cancel(obj)
    }

    fun close() {
        checkInit()
        sMProcessor.close()
    }

    fun processNetonAction(intent: Intent?) {
        checkInit()
        NetonReceiver.processNetonAction(mContext, intent)
    }

    companion object {
        val instance = NetonClient()
        private var sMProcessor: Processor? = null
        val processor: Processor?
            get() {
                checkInit()
                return sMProcessor
            }

        private fun checkInit() {
            if (sMProcessor == null) {
                throw NetonException(IllegalStateException("please init Neton first,or set region code when use http dns"))
            }
        }

        fun execute(request: Request?): Response {
            checkInit()
            return try {
                sMProcessor.process(request, false)
            } catch (e: IOException) {
                throw e
            } catch (e2: Exception) {
                throw NetonException(e2)
            }
        }

        fun executeAsync(request: Request?, callback: Callback?) {
            checkInit()
            try {
                sMProcessor.process(request, callback, false)
            } catch (e: Exception) {
                throw NetonException(e)
            }
        }

        operator fun get(str: String?): Response {
            return execute(Builder().get().url(str).build())
        }

        fun getString(str: String?): String {
            return execute(Builder().get().url(str).build()).body().string()
        }

        fun getAsync(str: String?, callback: Callback?) {
            executeAsync(Builder().url(str).build(), callback)
        }

        fun getAsync(str: String?, timeout: Timeout?, callback: Callback?) {
            executeAsync(Builder().timeout(timeout).url(str).build(), callback)
        }

        fun post(str: String?, str2: String?): Response {
            return execute(Builder().post(create(MediaType.parse(MediaType.TEXT_PLAIN), str2)).url(str).build())
        }

        fun post(str: String?, bArr: ByteArray?): Response {
            return execute(Builder().post(create(MediaType.parse(MediaType.OCTET_STREAM), bArr)).url(str).build())
        }

        fun postAsync(str: String?, str2: String?, callback: Callback?) {
            executeAsync(Builder().post(create(MediaType.parse(MediaType.TEXT_PLAIN), str2)).url(str).build(), callback)
        }

        fun postAsync(str: String?, bArr: ByteArray?, callback: Callback?) {
            executeAsync(Builder().post(create(MediaType.parse(MediaType.OCTET_STREAM), bArr)).url(str).build(), callback)
        }

        fun postAsync(str: String?, str2: String?, bArr: ByteArray?, timeout: Timeout?, callback: Callback?) {
            executeAsync(Builder().post(create(MediaType.parse(str2), bArr)).timeout(timeout).url(str).build(), callback)
        }

        fun post(str: String?, file: File?): Response {
            return execute(Builder().post(create(MediaType.parse(MediaType.OCTET_STREAM), file)).url(str).build())
        }

        fun postFileAsync(str: String?, file: File?, timeout: Timeout?, callback: Callback?) {
            executeAsync(Builder().post(create(MediaType.parse(MediaType.OCTET_STREAM), file)).timeout(timeout).url(str).build(), callback)
        }

        fun postFileAsync(str: String?, map: Map<String?, String?>?, map2: Map<String?, Any?>?, timeout: Timeout?, progressCallBack: ProgressCallBack?) {
            executeAsync(Builder().post(create(map2, progressCallBack)).headers(Headers.m6336of(map)).timeout(timeout).url(str).build(), object : Callback() {
                /* class com.coloros.neton.NetonClient.C12371 */
                // neton.Callback
                fun onFailure(call: Call?, iOException: IOException?) {
                    this@ProgressCallBack.onFailure(call, iOException)
                }

                // neton.Callback
                fun onResponse(call: Call?, response: Response) {
                    if (!response.isSuccessful()) {
                        this@ProgressCallBack.onFailure(call, IOException("response is not success!"))
                    }
                    this@ProgressCallBack.onResponse(call, response)
                }
            })
        }

        fun getFileAsync(str: String?, map: Map<String?, String?>?, file: File?, timeout: Timeout?, progressCallBack: ProgressCallBack?) {
            executeAsync(Builder().timeout(timeout).headers(Headers.m6336of(map)).url(str).build(), object : Callback() {
                /* class com.coloros.neton.NetonClient.C12382 */
                // neton.Callback
                fun onFailure(call: Call?, iOException: IOException?) {
                    this@ProgressCallBack.onFailure(call, iOException)
                }

                /* JADX WARNING: Removed duplicated region for block: B:36:0x008d A[SYNTHETIC, Splitter:B:36:0x008d] */ /* JADX WARNING: Removed duplicated region for block: B:41:0x0095 A[Catch:{ IOException -> 0x0091 }] */
                // neton.Callback
                /* Code decompiled incorrectly, please refer to instructions dump. */   fun onResponse(r11: neton.Call?, r12: neton.Response?) {
                    /*
                    r10 = this;
                    java.lang.String r0 = "getFileAsync--onResponse start"
                    neton.client.Utils.LogUtil.m6340d(r0)
                    r0 = 2048(0x800, float:2.87E-42)
                    byte[] r0 = new byte[r0]
                    r1 = 0
                    neton.ResponseBody r2 = r12.body()     // Catch:{ IOException -> 0x0086, all -> 0x0082 }
                    long r2 = r2.contentLength()     // Catch:{ IOException -> 0x0086, all -> 0x0082 }
                    r4 = 0
                    neton.ResponseBody r6 = r12.body()     // Catch:{ IOException -> 0x0086, all -> 0x0082 }
                    java.io.InputStream r6 = r6.byteStream()     // Catch:{ IOException -> 0x0086, all -> 0x0082 }
                    java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x007e, all -> 0x007b }
                    java.io.File r8 = r3     // Catch:{ IOException -> 0x007e, all -> 0x007b }
                    r7.<init>(r8)     // Catch:{ IOException -> 0x007e, all -> 0x007b }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    java.lang.String r8 = "getFileAsync--onResponse dstFile:"
                    r1.<init>(r8)     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    java.io.File r8 = r3     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    r1.append(r8)     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    neton.client.Utils.LogUtil.m6340d(r1)     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                L_0x0036:
                    int r1 = r6.read(r0)     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    r8 = -1
                    if (r1 == r8) goto L_0x0062
                    long r8 = (long) r1     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    long r4 = r4 + r8
                    r8 = 0
                    r7.write(r0, r8, r1)     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    java.lang.String r9 = "getFileAsync--current:"
                    r8.<init>(r9)     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    r8.append(r4)     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    java.lang.String r9 = ",len:"
                    r8.append(r9)     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    r8.append(r1)     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    java.lang.String r1 = r8.toString()     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    neton.client.Utils.LogUtil.m6340d(r1)     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    com.coloros.neton.ProgressCallBack r1 = com.coloros.neton.ProgressCallBack.this     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    r1.onProgress(r2, r4)     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    goto L_0x0036
                L_0x0062:
                    r7.flush()     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
                    if (r6 == 0) goto L_0x006d
                    r6.close()     // Catch:{ IOException -> 0x006b }
                    goto L_0x006d
                L_0x006b:
                    r10 = move-exception
                    goto L_0x0076
                L_0x006d:
                    r7.close()     // Catch:{ IOException -> 0x006b }
                    com.coloros.neton.ProgressCallBack r10 = com.coloros.neton.ProgressCallBack.this
                    r10.onResponse(r11, r12)
                    return
                L_0x0076:
                    throw r10
                L_0x0077:
                    r10 = move-exception
                    goto L_0x008b
                L_0x0079:
                    r10 = move-exception
                    goto L_0x0080
                L_0x007b:
                    r10 = move-exception
                    r7 = r1
                    goto L_0x008b
                L_0x007e:
                    r10 = move-exception
                    r7 = r1
                L_0x0080:
                    r1 = r6
                    goto L_0x0088
                L_0x0082:
                    r10 = move-exception
                    r6 = r1
                    r7 = r6
                    goto L_0x008b
                L_0x0086:
                    r10 = move-exception
                    r7 = r1
                L_0x0088:
                    throw r10     // Catch:{ all -> 0x0089 }
                L_0x0089:
                    r10 = move-exception
                    r6 = r1
                L_0x008b:
                    if (r6 == 0) goto L_0x0093
                    r6.close()     // Catch:{ IOException -> 0x0091 }
                    goto L_0x0093
                L_0x0091:
                    r10 = move-exception
                    goto L_0x0099
                L_0x0093:
                    if (r7 == 0) goto L_0x009a
                    r7.close()     // Catch:{ IOException -> 0x0091 }
                    goto L_0x009a
                L_0x0099:
                    throw r10
                L_0x009a:
                    throw r10
                */
                    throw UnsupportedOperationException("Method not decompiled: com.coloros.neton.NetonClient.C12382.onResponse(neton.Call, neton.Response):void")
                }
            })
        }
    }
}