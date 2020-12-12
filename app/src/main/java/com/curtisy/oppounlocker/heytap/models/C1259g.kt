package com.curtisy.oppounlocker.heytap.models

import android.os.Handler
import android.os.Message
import com.curtisy.oppounlocker.heytap.CallInfoAgent
import okhttp3.internal.wait


class C1259g internal constructor(/* renamed from: a */
    /* synthetic */val f6900a: CallInfoAgent, i: Int, str: String
) : Thread() {
    /* renamed from: b */
    private var f6901b = 0

    /* renamed from: c */
    private val f6902c: String
    override fun run() {
        synchronized(f6900a.f6891b) {
            if (CallInfoAgent.f6889h == null) {
                try {
                    f6900a.f6891b.wait()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        var userEntity: UserEntity? = UserEntity()
        when (f6901b) {
            1 -> userEntity = f6900a.mo6890a(f6902c)
            2 -> userEntity = f6900a.mo6895b(f6902c)
            3 -> userEntity = f6900a.mo6899c(f6902c)
        }
        f6900a.mo6901e()
        if (!(userEntity == null || CallInfoAgent.f6887c == null)) {
            val message = Message()
            message.obj = userEntity
            val handler: Handler? = CallInfoAgent.f6887c
            if (!(handler == null || CallInfoAgent.f6887c == null)) {
                handler.sendMessage(message)
            }
        }
        CallInfoAgent.f6889h = null
        CallInfoAgent.f6887c = null
    }

    init {
        f6901b = i
        f6902c = str
    }
}