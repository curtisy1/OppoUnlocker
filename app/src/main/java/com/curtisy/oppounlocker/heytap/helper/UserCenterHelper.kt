package com.curtisy.oppounlocker.heytap.helper

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.FEATURE_OPENGLES_EXTENSION_PACK
import android.content.pm.PackageManager.NameNotFoundException
import android.net.Uri
import android.os.Handler
import com.curtisy.oppounlocker.heytap.account.AccountAgent


object UserCenterHelper {
    /* renamed from: a */
    private val f6753a =
        Uri.parse("content://com.heytap.usercenter.provider.open/DBAccountStatusEntity")

    /* renamed from: b */
    private val f6754b = arrayOf("accountStatus")

    /* renamed from: a */
    fun m6037a(context: Context): Boolean {
        return AccountAgent.isLogin(context, context.packageName)
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006c, code lost:
        if ((r0 != null && r0.size() > 0) != false) goto L_0x0070;
     */
    /* renamed from: a */ /* Code decompiled incorrectly, please refer to instructions dump. */
    fun m6036a(context: Context, handler: Handler) {
        val oppoUserCenterExists = OppoUserCenterExists(context)
        if(!oppoUserCenterExists) {
            return
        }

        requestToken(context, handler)
    }

    private fun requestToken(context: Context, handler: Handler) {
        // TODO this doesn't make sense.. why would you send a request when you're not logged in?
        AccountAgent.reqToken(context, handler, "3000")
    }

    /* renamed from: b */
    private fun OppoUserCenterExists(context: Context): Boolean {
        val applicationInfo: ApplicationInfo?
        val applicationInfo2: ApplicationInfo?
        val packageManager = context.packageManager
        applicationInfo = try {
            packageManager.getApplicationInfo("com.oppo.usercenter", PackageManager.MATCH_UNINSTALLED_PACKAGES)
        } catch (e: NameNotFoundException) {
            e.printStackTrace()
            null
        }
        applicationInfo2 = try {
            packageManager.getApplicationInfo("com.heytap.usercenter", PackageManager.MATCH_UNINSTALLED_PACKAGES)
        } catch (e2: NameNotFoundException) {
            e2.printStackTrace()
            null
        }
        return !(applicationInfo == null && applicationInfo2 == null)
    }
}
