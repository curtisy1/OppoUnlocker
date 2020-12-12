package com.curtisy.oppounlocker.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Messenger
import com.curtisy.oppounlocker.data.C1234b
import com.curtisy.oppounlocker.data.UnlockStatus
import com.curtisy.oppounlocker.heytap.Constants
import com.curtisy.oppounlocker.heytap.NetonClient
import com.curtisy.oppounlocker.heytap.config.NetonConfig
import com.curtisy.oppounlocker.utilities.AesEncryptUtils
import com.curtisy.oppounlocker.utilities.Utils
import com.google.gson.Gson
import java.nio.charset.Charset


class RequestService : Service() {
    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler
    private lateinit var requestUrl: String

    var messenger: Messenger? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        internalContext = this
        context = applicationContext
        val applicationContext = applicationContext
        try {
            NetonClient.instance.init(applicationContext, NetonConfig.Builder().dnsMode(1).build())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        AesEncryptUtils.m6015a()
        handlerThread = HandlerThread("Request")
        handlerThread.start()
        if (handlerThread.looper != null) {
            handler = ServiceHandler(this, handlerThread.looper)
        } else {
            stopSelf()
        }
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, i: Int, i2: Int): Int {
        if (intent == null || intent.extras == null) {
            return super.onStartCommand(intent, i, i2)
        }
        messenger = intent.extras!!["Messenger"] as Messenger?
        requestUrl = "https://ilk.apps.coloros.com/api/v2/"
        when (intent.extras!!["MessengerFlag"] as Int?) {
            1000 -> requestUrl += "apply-unlock"
            1001 -> requestUrl += "check-approve-result"
            Constants.USERCENTER_PLUGIN_ID -> requestUrl += "update-client-lock-status"
            1003 -> requestUrl += "get-all-status"
            1004 -> requestUrl += "lock-client"
        }
        handler.sendEmptyMessage(0)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        handlerThread.quit()
    }

    /* renamed from: a */
    fun getUnlockStatus(): UnlockStatus? {
        val bVar = C1234b()
        bVar.mo6785a(internalContext)
        return try {
            Gson().fromJson(decryptJsonFromServer(Gson().toJson(bVar)), UnlockStatus::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun decryptJsonFromServer(requestString: String): String {
        var r4 = AesEncryptUtils.m6012a(requestString)
        var r2 = Utils.m6040a(this, requestUrl, r4)
        if(r2 != null) {
            r2.code
            var r3 = r2.body?.bytes()
            if(r3 != null && r3.isNotEmpty()) {
                var str = String(r3, Charset.forName("UTF-8"))
                var enc = AesEncryptUtils.decrypt(str)
                r2.close()
                return enc
            }
        }

        return ""
    }

    companion object {
        private var internalContext: Context? = null
        lateinit var context: Context
    }
}