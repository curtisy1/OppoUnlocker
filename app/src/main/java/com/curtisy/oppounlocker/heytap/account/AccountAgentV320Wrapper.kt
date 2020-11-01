package com.curtisy.oppounlocker.heytap.account

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.heytap.utils.ApkInfoUtil
import com.curtisy.oppounlocker.heytap.Constants
import com.curtisy.oppounlocker.heytap.models.C0854b
import com.curtisy.oppounlocker.heytap.models.UserEntity
import com.curtisy.oppounlocker.heytap.usercenter.UCHeyTapAccountProvider.providerUrlUsercenterOpOpenXor8

object AccountAgentV320 {
    /* renamed from: a */
    val f4550a = providerUrlUsercenterOpOpenXor8

    /* renamed from: b */
    val f4551b = Uri.parse(f4550a + "/DBAccountEntity")

    /* renamed from: c */
    val f4552c = Uri.parse(f4550a + "/DBSecondaryTokenEntity")

    /* renamed from: d */
    val f4553d = arrayOf("accountName", "authToken", ApkInfoUtil.SSOID)

    /* renamed from: e */
    val f4554e = arrayOf("accountName", "authToken", ApkInfoUtil.SSOID, "deviceId")

    /* renamed from: a */
    fun m4619a(bVar: C0854b?): Boolean {
        return bVar != null && !TextUtils.isEmpty(bVar.f4555a) && !TextUtils.isEmpty(bVar.f4556b)
    }

    /* renamed from: a */
    fun m4618a(context: Context?, handler: Handler?) {
        if (context != null && handler != null) {
            val a = m4617a(context)
            if (a != null) {
                val message = Message()
                message.obj = UserEntity(Constants.REQ_SUCCESS, "success", a.f4555a, a.f4556b)
                handler.sendMessage(message)
                return
            }

            val message2 = Message()
            message2.obj = UserEntity(Constants.REQ_FAILED, "fail", "", "")
            handler.sendMessage(message2)
        }
    }

    // TODO: This was reconstructed from smali. Not necessarily correct
    fun m4617a(context: Context): C0854b? {
        var packageManager = context.packageManager
        var intent = Intent("com.usercenter.action.provider.TOKEN_SAFE")
        var providers = packageManager?.queryIntentContentProviders(intent, 0)
        var r4 = 0
        val cursor: Cursor?
        if(providers != null && providers.size <= 0) {
            var contentResolver = context.contentResolver
            var uri = f4552c
            var f4554e = f4554e
            var pkgnameStr = "pkgName = ?"
            var stringArray = arrayOfNulls<String>(1)
            var applicationContext = context.applicationContext
            var packageName = applicationContext?.packageName
            stringArray[0] = packageName
            cursor = contentResolver?.query(uri, f4554e, pkgnameStr, stringArray, null)
        } else {
            var contentResolver = context.contentResolver
            var uri = f4551b
            var f4553d = f4553d
            var objectArray = arrayOfNulls<String>(1)
            var firstCharOfF4553d = f4553d[0]
            objectArray[0] = firstCharOfF4553d
            var queryString = "(%s is not null)"
            var formattedQuery = String.format(queryString, objectArray)
            var objectArray2 = arrayOfNulls<String>(1)
            var secondCharOfF4553d = f4553d[1]
            objectArray2[0] = secondCharOfF4553d
            var queryString2 = "(%s is not null)"
            var formattedQuery2 = String.format(queryString2, objectArray2)
            var objectArray3 = arrayOfNulls<String>(2)
            objectArray3[0] = formattedQuery
            objectArray3[1] = formattedQuery2
            var thirdQueryString = "%s AND %s"
            var formattedQueryString3 = String.format(thirdQueryString, objectArray3)
            cursor = contentResolver?.query(uri, f4553d, formattedQueryString3, null, null)
        }

        if(cursor == null || cursor.count <= 0) {
            return null
        }

        cursor.moveToFirst()
        var c0854b = C0854b()
        var anotherStrangeValue = f4553d
        var charAtIndex = anotherStrangeValue[0]
        var columnIndex = cursor.getColumnIndex(charAtIndex)
        var cursorString = cursor.getString(columnIndex)
        c0854b.f4555a = cursorString
        charAtIndex = anotherStrangeValue[1]
        columnIndex = cursor.getColumnIndex(charAtIndex)
        cursorString = cursor.getString(columnIndex)
        c0854b.f4556b = cursorString
        charAtIndex = anotherStrangeValue[2]
        columnIndex = cursor.getColumnIndex(charAtIndex)
        cursorString = cursor.getString(columnIndex)
        c0854b.f4557c = cursorString

        if(providers != null) {
            charAtIndex = f4554e[3]
            columnIndex = cursor.getColumnIndex(charAtIndex)
            cursorString = cursor.getString(columnIndex)
            c0854b.f4558d = cursorString
        }


        cursor.close()
        return c0854b
    }
}