package com.curtisy.oppounlocker.heytap.models

import android.os.Message
import com.curtisy.oppounlocker.heytap.CallInfoAgent
import okhttp3.internal.wait


class C1260h internal constructor(/* renamed from: a */
    /* synthetic */val f6903a: CallInfoAgent, i: Int
) : Thread() {
    /* renamed from: b */
    private var f6904b = 0
    override fun run() {
        synchronized(f6903a.f6891b) {
            if (CallInfoAgent.f6888e == null) {
                try {
                    f6903a.f6891b.wait()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        var userEntity: UserEntity? = UserEntity()
        when (f6904b) {
            1 -> userEntity = f6903a.mo6894b()
            2 -> userEntity = f6903a.mo6898c()
            3 -> userEntity = f6903a.mo6900d()
        }
        f6903a.mo6891a()
        if (!(userEntity == null || CallInfoAgent.f6887c == null)) {
            val message = Message()
            message.obj = userEntity
            val handler = CallInfoAgent.f6887c
            if (!(handler == null || CallInfoAgent.f6887c == null)) {
                handler.sendMessage(message)
            }
        }
        CallInfoAgent.f6888e = null
        CallInfoAgent.f6887c = null
    }

    init {
        f6904b = i
    }
}