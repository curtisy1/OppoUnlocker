package com.curtisy.oppounlocker.heytap.usercenter

import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.heytap.utils.ApkInfoUtil
import com.curtisy.oppounlocker.heytap.utils.SystemPropertyUtils
import com.curtisy.oppounlocker.utilities.XORUtils


object UCOSVersionUtil {
    fun m6283a(): Int {
        return try {
            val cls = Class.forName(XORUtils.hash("kge&kgdgz&g{&KgdgzJ}adl")) ?: return 0
            (cls.getDeclaredMethod(XORUtils.hash("om|KgdgzG[^MZ[AGF"), *arrayOfNulls<Class<*>>(0)).invoke(cls, *arrayOfNulls(0)) as Int).toInt()
        } catch (unused: Exception) {
            0
        }
    }

    /* renamed from: b */
    fun m6284b(): String? {
        val a: String = SystemPropertyUtils.getProp(XORUtils.hash("xmz{a{|&{q{&gxxg&zmoagf"), ApkInfoUtil.OPPO_VERSION_CN)
        return if ("OC".equals(a, ignoreCase = true)) ApkInfoUtil.OPPO_VERSION_CN else a
    }

    /* renamed from: c */
    fun m6285c(): String? {
        return SystemPropertyUtils.getProp(XORUtils.hash("zg&j}adl&~mz{agf&gxxgzge"), BuildConfig.FLAVOR)
    }
}
