package com.curtisy.oppounlocker.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Messenger
import com.curtisy.oppounlocker.data.C1234b
import com.curtisy.oppounlocker.heytap.Constants
import com.curtisy.oppounlocker.heytap.NetonClient
import com.curtisy.oppounlocker.heytap.config.NetonConfig
import com.curtisy.oppounlocker.utilities.AesEncryptUtils
import com.google.gson.Gson


class RequestService : Service() {
    private var f6809c: HandlerThread? = null

    private var f6810d: Handler? = null

    var f6811e: Messenger? = null

    private var f6812f: String? = null
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        f6807a = this
        f6808b = applicationContext
        val applicationContext: Context = applicationContext
        try {
            NetonClient.getInstance().init(applicationContext, NetonConfig.Builder().dnsMode(1).build())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        AesEncryptUtils.m6015a()
        f6809c = HandlerThread("Request")
        f6809c!!.start()
        if (f6809c!!.looper != null) {
            f6810d = HandlerC1233a(this, f6809c!!.looper)
        } else {
            stopSelf()
        }
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, i: Int, i2: Int): Int {
        if (intent == null || intent.extras == null) {
            return super.onStartCommand(intent, i, i2)
        }
        f6811e = intent.extras!!["Messenger"] as Messenger?
        f6812f = "https://ilk.apps.coloros.com/api/v2/"
        when (intent.extras!!["MessengerFlag"] as Int?) {
            1000 -> f6812f += "apply-unlock"
            1001 -> f6812f += "check-approve-result"
            Constants.USERCENTER_PLUGIN_ID -> f6812f += "update-client-lock-status"
            1003 -> f6812f += "get-all-status"
            1004 -> f6812f += "lock-client"
        }
        f6810d.sendEmptyMessage(0)
        return Service.START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        f6809c!!.quit()
    }

    /* renamed from: a */
    fun mo6779a(): C1235c? {
        val bVar = C1234b()
        bVar.mo6785a(f6807a)
        return try {
            Gson().mo5620a(m6081a(f6812f, Utils.m6039a(bVar)), C1235c::class.java) as C1235c?
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x0085 A[SYNTHETIC, Splitter:B:49:0x0085] */ /* JADX WARNING: Removed duplicated region for block: B:56:0x0091 A[SYNTHETIC, Splitter:B:56:0x0091] */ /* renamed from: a */ /* Code decompiled incorrectly, please refer to instructions dump. */
    private fun m6081a(r3: String?, r4: String): String {
        /*
            r2 = this;
            r0 = 0
            if (r3 != 0) goto L_0x000b
            java.lang.String r2 = "RequestService"
            java.lang.String r3 = "request url is null"
            com.coloros.deeptesting.p059a.OppoLog.m6033a(r2, r3)
            return r0
        L_0x000b:
            if (r4 != 0) goto L_0x0015
            java.lang.String r2 = "RequestService"
            java.lang.String r3 = "request string is null"
            com.coloros.deeptesting.p059a.OppoLog.m6033a(r2, r3)
            return r0
        L_0x0015:
            java.lang.String r4 = com.coloros.deeptesting.p059a.AesEncryptUtils.m6012a(r4)
            if (r4 != 0) goto L_0x0023
            java.lang.String r2 = "RequestService"
            java.lang.String r3 = " json request is null after encrypt"
            com.coloros.deeptesting.p059a.OppoLog.m6033a(r2, r3)
            return r0
        L_0x0023:
            neton.Response r2 = com.coloros.deeptesting.p059a.Utils.m6040a(r2, r3, r4)     // Catch:{ Exception -> 0x0077, all -> 0x0074 }
            if (r2 != 0) goto L_0x003d
            java.lang.String r3 = "RequestService"
            java.lang.String r4 = "neton response is null"
            com.coloros.deeptesting.p059a.OppoLog.m6033a(r3, r4)     // Catch:{ Exception -> 0x003b }
            if (r2 == 0) goto L_0x003a
            r2.close()     // Catch:{ NetonException -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r2 = move-exception
            r2.printStackTrace()
        L_0x003a:
            return r0
        L_0x003b:
            r3 = move-exception
            goto L_0x0079
        L_0x003d:
            r2.code()
            neton.ResponseBody r3 = r2.body()
            byte[] r3 = r3.bytes()
            if (r3 == 0) goto L_0x0050
            int r4 = r3.length
            if (r4 != 0) goto L_0x004e
            goto L_0x0050
        L_0x004e:
            r4 = 0
            goto L_0x0051
        L_0x0050:
            r4 = 1
        L_0x0051:
            if (r4 != 0) goto L_0x0068
            java.lang.String r4 = new java.lang.String
            java.lang.String r1 = "UTF-8"
            r4.<init>(r3, r1)
            java.lang.String r3 = com.coloros.deeptesting.p059a.AesEncryptUtils.m6017b(r4)
            if (r3 != 0) goto L_0x0069
            java.lang.String r4 = "RequestService"
            java.lang.String r1 = "decryptJsonFromServer reslut is null"
            com.coloros.deeptesting.p059a.OppoLog.m6033a(r4, r1)
            goto L_0x0069
        L_0x0068:
            r3 = r0
        L_0x0069:
            if (r2 == 0) goto L_0x0073
            r2.close()     // Catch:{ NetonException -> 0x006f }
            goto L_0x0073
        L_0x006f:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0073:
            return r3
        L_0x0074:
            r3 = move-exception
            r2 = r0
            goto L_0x008f
        L_0x0077:
            r3 = move-exception
            r2 = r0
        L_0x0079:
            java.lang.String r4 = "RequestService"
            java.lang.String r1 = "post to server occur"
            com.coloros.deeptesting.p059a.OppoLog.m6035c(r4, r1)     // Catch:{ all -> 0x008e }
            r3.printStackTrace()     // Catch:{ all -> 0x008e }
            if (r2 == 0) goto L_0x008d
            r2.close()     // Catch:{ NetonException -> 0x0089 }
            goto L_0x008d
        L_0x0089:
            r2 = move-exception
            r2.printStackTrace()
        L_0x008d:
            return r0
        L_0x008e:
            r3 = move-exception
        L_0x008f:
            if (r2 == 0) goto L_0x0099
            r2.close()     // Catch:{ NetonException -> 0x0095 }
            goto L_0x0099
        L_0x0095:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0099:
            throw r3
        */
        throw UnsupportedOperationException("Method not decompiled: com.coloros.deeptesting.service.RequestService.m6081a(java.lang.String, java.lang.String):java.lang.String")
    }

    companion object {
        /* renamed from: a */
        private var f6807a: Context? = null

        /* renamed from: b */
        var f6808b: Context? = null
    }
}