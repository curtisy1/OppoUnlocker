package com.curtisy.oppounlocker.heytap.usercenter

import android.content.Context
import android.os.Build
import com.curtisy.oppounlocker.BuildConfig
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
            f7017d = context.packageManager.hasSystemFeature("com.oneplus.mobilephone") || XORUtils.m6334a("gfmxd}{").equalsIgnoreCase(Build.BRAND) || "Kepler".equals(Build.BRAND, ignoreCase = true)
            val equalsIgnoreCase = "OverSeas".equals(SystemPropertyUtils.m6294a("persist.sys.oem.region", BuildConfig.FLAVOR), ignoreCase = true)
            z = !(f7017d || (if (f7015b <= 9) !("US".equals(SystemPropertyUtils.m6294a(XORUtils.m6334a("zg&gxxg&~mz{agf"), BuildConfig.FLAVOR), ignoreCase = true) || equalsIgnoreCase) else !(context.packageManager.hasSystemFeature(XORUtils.m6334a("gxxg&~mz{agf&mpx")) || equalsIgnoreCase)))
            if (!equalsIgnoreCase && !z) {
                z2 = false
            }
            f7014a = z2
        }

        /* renamed from: a */
        fun m6286a(): String? {
            if (f7016c) {
                return XORUtils.m6334a("Zmidem")
            }
            return if (f7017d) {
                XORUtils.m6334a("gfmxd}{")
            } else XORUtils.m6334a("GXXG")
        }
    }
}