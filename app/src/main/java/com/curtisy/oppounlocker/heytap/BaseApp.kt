package com.curtisy.oppounlocker.heytap

import android.content.Context
import com.curtisy.oppounlocker.heytap.usercenter.UCRuntimeEnvironment

class BaseApp {
    companion object {
        var f7020a: Context? = null

        /* renamed from: a */
        fun m6293a(context: Context?) {
            if (context == null) {
                return
            }
            f7020a = context
            UCRuntimeEnvironment.m6287a(context)
        }
    }
}