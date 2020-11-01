package com.curtisy.oppounlocker.heytap.account

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Messenger
import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.helper.ApkInfoHelper.m6318a
import com.curtisy.oppounlocker.helper.ApkInfoHelper.m6319b
import com.curtisy.oppounlocker.heytap.Constants
import com.curtisy.oppounlocker.heytap.models.AppInfo
import com.curtisy.oppounlocker.heytap.usercenter.UCHeyTapAccountProvider
import com.curtisy.oppounlocker.heytap.usercenter.UCHeyTapAccountProvider.extraRequestBindMessengerKey
import com.curtisy.oppounlocker.heytap.usercenter.UCHeyTapAccountProvider.providerUsercenterAutologinServiceXor8
import com.curtisy.oppounlocker.heytap.usercenter.UCHeyTapAccountProvider.providerUsercenterBindPageXor8
import com.curtisy.oppounlocker.heytap.usercenter.UCHeyTapAccountProvider.providerUsercenterModifyAccountnameXor8
import com.curtisy.oppounlocker.heytap.usercenter.UCHeyTapAccountProvider.providerUsercenterModifyFullnameXor8
import com.curtisy.oppounlocker.heytap.usercenter.UCHeyTapCommonProvider
import com.curtisy.oppounlocker.utilities.XORUtils


object AccountHelper {
    fun getAppInfo(context: Context, str: String?): AppInfo {
        val appInfo = AppInfo()
        appInfo.appCode = str
        appInfo.packageName = context.packageName
        appInfo.secreKey = BuildConfig.FLAVOR
        appInfo.appVersion = m6318a(context, context.packageName)
        return appInfo
    }

    @SuppressLint("WrongConstant")
    fun startBindInfoPage(context: Context, handler: Handler?, str: String?) {
        val intent = Intent(providerUsercenterBindPageXor8)
        intent.putExtra(extraRequestBindMessengerKey, Messenger(handler))
        intent.putExtra(Constants.EXTRA_ACTION_APPINFO_KEY, AppInfo.toJson(getAppInfo(context, str)))
        if (context !is Activity) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (m6319b(context, UCHeyTapCommonProvider.m6288a("kge&`mq|ix&}{mzkmf|mz"))) {
            intent.setPackage(UCHeyTapCommonProvider.m6288a("kge&`mq|ix&}{mzkmf|mz"))
        } else {
            intent.setPackage(UCHeyTapCommonProvider.m6288a("kge&gxxg&}{mzkmf|mz"))
        }
        if (context.packageManager.resolveActivity(intent, Intent.FLAG_ACTIVITY_NO_ANIMATION) != null) {
            context.startActivity(intent)
        }
    }

    fun startModifyAccountNameActivity(activity: Activity, str: String?): Boolean {
        val intent = Intent(providerUsercenterModifyAccountnameXor8)
        val appInfo = getAppInfo(activity, str)
        if (m6319b(activity, UCHeyTapCommonProvider.m6288a("kge&`mq|ix&}{mzkmf|mz"))) {
            intent.setPackage(UCHeyTapCommonProvider.m6288a("kge&`mq|ix&}{mzkmf|mz"))
        } else {
            intent.setPackage(UCHeyTapCommonProvider.m6288a("kge&gxxg&}{mzkmf|mz"))
        }
        intent.putExtra(Constants.EXTRA_ACTION_APPINFO_KEY, AppInfo.toJson(appInfo))
        return try {
            activity.startActivityForResult(intent, Constants.REQUEST_CODE_MODIFY_ACCOUNTNAME)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun startModifyFullNameActivity(activity: Activity, str: String?): Boolean {
        val intent = Intent(providerUsercenterModifyFullnameXor8)
        val appInfo = getAppInfo(activity, str)
        if (m6319b(activity, UCHeyTapCommonProvider.m6288a("kge&`mq|ix&}{mzkmf|mz"))) {
            intent.setPackage(UCHeyTapCommonProvider.m6288a("kge&`mq|ix&}{mzkmf|mz"))
        } else {
            intent.setPackage(UCHeyTapCommonProvider.m6288a("kge&gxxg&}{mzkmf|mz"))
        }
        intent.putExtra(Constants.EXTRA_ACTION_APPINFO_KEY, AppInfo.toJson(appInfo))
        return try {
            activity.startActivityForResult(intent, 113)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    @SuppressLint("WrongConstant")
    fun startReqAutoLoginService(context: Context, str: String?) {
        val intent = Intent(providerUsercenterAutologinServiceXor8)
        intent.putExtra(Constants.EXTRA_ACTION_APPINFO_KEY, AppInfo.toJson(getAppInfo(context, str)))
        if (m6319b(context, UCHeyTapCommonProvider.m6288a("kge&`mq|ix&}{mzkmf|mz"))) {
            intent.setPackage(UCHeyTapCommonProvider.m6288a("kge&`mq|ix&}{mzkmf|mz"))
        } else {
            intent.setPackage(UCHeyTapCommonProvider.m6288a("kge&gxxg&}{mzkmf|mz"))
        }
        if (context.packageManager.resolveService(intent, Intent.FLAG_ACTIVITY_NO_ANIMATION) != null) {
            context.startService(intent)
        }
    }

    @SuppressLint("WrongConstant")
    fun startReqSignInActivity(context: Context, str: String?) {
        val intent = Intent(UCHeyTapAccountProvider.providerIntfUsercenterOpenContainerActivityXor8)
        intent.putExtra(Constants.EXTRA_ACTION_APPINFO_KEY, AppInfo.toJson(getAppInfo(context, str)))
        intent.putExtra(Constants.EXTRA_REQUEST_TYPE_KEY, Constants.REQUSET_TYPE_REQRESIGNIN)
        intent.flags = 536870912
        if (context !is Activity) {
            intent.addFlags(268435456)
        }
        if (m6319b(context, UCHeyTapCommonProvider.m6288a("kge&`mq|ix&}{mzkmf|mz"))) {
            intent.setPackage(UCHeyTapCommonProvider.m6288a("kge&`mq|ix&}{mzkmf|mz"))
        } else {
            intent.setPackage(UCHeyTapCommonProvider.m6288a("kge&gxxg&}{mzkmf|mz"))
        }
        if (context.packageManager.resolveActivity(intent, 65536) != null) {
            context.startActivity(intent)
        }
    }

    @SuppressLint("WrongConstant")
    fun startReqSwitchAccountActivity(context: Context, str: String?) {
        val intent = Intent(UCHeyTapAccountProvider.providerIntfUsercenterOpenContainerActivityXor8)
        intent.putExtra(Constants.EXTRA_ACTION_APPINFO_KEY, AppInfo.toJson(getAppInfo(context, str)))
        intent.putExtra(Constants.EXTRA_REQUEST_TYPE_KEY, Constants.REQUSET_TYPE_REQSWITCH_ACCOUNT)
        intent.flags = 536870912
        if (context !is Activity) {
            intent.addFlags(268435456)
        }
        if (m6319b(context, UCHeyTapCommonProvider.m6288a("kge&`mq|ix&}{mzkmf|mz"))) {
            intent.setPackage(UCHeyTapCommonProvider.m6288a("kge&`mq|ix&}{mzkmf|mz"))
        } else {
            intent.setPackage(UCHeyTapCommonProvider.m6288a("kge&gxxg&}{mzkmf|mz"))
        }
        if (context.packageManager.resolveActivity(intent, 65536) != null) {
            context.startActivity(intent)
        }
    }

    @SuppressLint("WrongConstant")
    fun startReqTokenActivity(context: Context, str: String?, z: Boolean) {
        val intent = Intent(UCHeyTapAccountProvider.providerIntfUsercenterOpenContainerActivityXor8)
        intent.putExtra(Constants.EXTRA_ACTION_APPINFO_KEY, AppInfo.toJson(getAppInfo(context, str)))
        intent.putExtra(Constants.EXTRA_REQUEST_TYPE_KEY, Constants.REQUSET_TYPE_REQTOKEN)
        intent.putExtra(Constants.EXTRA_ACTION_AUTO_LOGIN_KEY, z)
        intent.putExtra(Constants.EXTRA_KEY_USERCENTER_PLUGIN_KEY, Constants.USERCENTER_PLUGIN_ID)
        intent.flags = 536870912
        if (context !is Activity) {
            intent.addFlags(268435456)
        }
        if (m6319b(context, UCHeyTapCommonProvider.m6288a("kge&`mq|ix&}{mzkmf|mz"))) {
            intent.setPackage(UCHeyTapCommonProvider.m6288a("kge&`mq|ix&}{mzkmf|mz"))
        } else {
            intent.setPackage(UCHeyTapCommonProvider.m6288a("kge&gxxg&}{mzkmf|mz"))
        }
        if (context.packageManager.resolveActivity(intent, 65536) != null) {
            context.startActivity(intent)
        }
    }

    fun getUserCenterVersionCode(context: Context): Int {
        val a = m6318a(context, XORUtils.hash("kge&`mq|ix&}{mzkmf|mz"))
        return if (a > 0) {
            a
        } else m6318a(context, XORUtils.hash("kge&gxxg&}{mzkmf|mz"))
    }

    fun getUCServiceVersionCode(context: Context): Int {
        return m6318a(context, XORUtils.hash("kge&gxxg&{mz~akm&ikkg}f|"))
    }
}