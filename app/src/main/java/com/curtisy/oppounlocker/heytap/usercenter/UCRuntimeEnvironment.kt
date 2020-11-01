package com.curtisy.oppounlocker.heytap.usercenter

import android.content.Context
import android.os.Build
import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.heytap.utils.SystemPropertyUtils
import com.curtisy.oppounlocker.utilities.XORUtils


class UCRuntimeEnvironment {
    companion object {
        var f7014a = false

        /* renamed from: b */
        var f7015b = 0

        /* renamed from: c */
        var f7016c = false

        /* renamed from: d */
        var f7017d = false

        /* renamed from: a */
        fun m6287a(context: Context) {
            val z: Boolean
            f7015b = UCOSVersionUtil.m6283a()
            f7016c = UCDeviceInfoUtil.m6312c()
            var z2 = true
            f7017d = context.packageManager.hasSystemFeature("com.oneplus.mobilephone") || XORUtils.hash("gfmxd}{").equals(Build.BRAND, ignoreCase = true) || "Kepler".equals(Build.BRAND, ignoreCase = true)
            val equalsIgnoreCase = "OverSeas".equals(SystemPropertyUtils.getProp("persist.sys.oem.region", ""), ignoreCase = true)
            z = !(f7017d || (if (f7015b <= 9) !("US".equals(SystemPropertyUtils.getProp(XORUtils.hash("zg&gxxg&~mz{agf"), ""), ignoreCase = true) || equalsIgnoreCase) else !(context.packageManager.hasSystemFeature(XORUtils.hash("gxxg&~mz{agf&mpx")) || equalsIgnoreCase)))
            if (!equalsIgnoreCase && !z) {
                z2 = false
            }
            f7014a = z2
        }

        /* renamed from: a */
        fun m6286a(): String? {
            if (f7016c) {
                return XORUtils.hash("Zmidem")
            }
            return if (f7017d) {
                XORUtils.hash("gfmxd}{")
            } else XORUtils.hash("GXXG")
        }
    }
}