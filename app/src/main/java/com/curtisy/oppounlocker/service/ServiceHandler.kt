package com.curtisy.oppounlocker.service

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.RemoteException
import com.curtisy.oppounlocker.MainActivity


internal class ServiceHandler(val f6813a: RequestService, looper: Looper) : Handler(looper) {
    override fun handleMessage(message: Message) {
        val message2 = Message()
        val a = f6813a.mo6779a()
        if (a == null) {
            message2.what = 100
        } else {
            message2.what = a.code
            message2.obj = a.message
            if (a.data != null) {
                when {
                    a.data!!.unlockCode != null -> {
                        message2.obj = a.data!!.unlockCode
                    }
                    a.data!!.exceptPassTime != null -> {
                        message2.what = 0
                        message2.obj = a.data!!.exceptPassTime
                    }
                    else -> {
                        message2.obj = Integer.valueOf(a.data!!.clientStatus)
                        var z = true
                        if (a.data!!.applyStatus != 1) {
                            z = false
                        }
                        MainActivity.f6768j = z
                    }
                }
            }
        }

        try {
            f6813a.f6811e!!.send(message2)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }
}
