package com.curtisy.oppounlocker.utilities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Messenger
import android.text.TextUtils
import com.curtisy.oppounlocker.heytap.NetonClient
import com.curtisy.oppounlocker.heytap.http.Request
import com.curtisy.oppounlocker.service.RequestService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*


class Utils {
    companion object {
        fun m6048c(): String {
            val stringBuilder = StringBuilder()
            val serialIdPath = "proc/oppoVersion/serialID"
            val fileInputStream = FileInputStream(serialIdPath)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            val line = bufferedReader.readLine()
            while (!TextUtils.isEmpty(line)) {
                stringBuilder.append(line)
            }

            bufferedReader.close()

            return stringBuilder.toString()
        }

        // TODO: Replace all of those with values specific to your phone
        fun m6040a(context: Context?, str: String?, str2: String?): Response? {
            return if (str == null || str2 == null) {
                null
            } else try {
                NetonClient.execute(
                        Request.Builder()
                                .url(str)
                                .addHeader("model", "PDEM30")
                                .addHeader("otaVersion", "PDEM10_11.A.17_0470_202009091604")
                                .addHeader("language", Locale.getDefault().toLanguageTag())
                                .addHeader("key", AesEncryptUtils.m6011a(context!!))
                                .post(str2.toRequestBody("JSON".toMediaTypeOrNull()))
                                .build()
                )
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        @SuppressLint("WrongConstant")
        fun m6046b(context: Context): Int {
            return try {
                    // TODO: Needs to be precompiled into a jre
                (context.getSystemService("persistent_data_block")).getFlashLockState()
            } catch (unused: SecurityException) {
                1
            }
        }

        fun m6044a(context: Context): Boolean {
            val activeNetworkInfo = (context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isAvailable
        }

        fun startRequestService(context: Context, messengerFlag: Int, handler: Handler?) {
            val intent = Intent(context, RequestService::class.java)
            intent.putExtra("MessengerFlag", messengerFlag)
            intent.putExtra("Messenger", Messenger(handler))
            context.startService(intent)
        }

        fun fastbootUnlock(): Boolean {
            val bArr = byteArrayOf(0)
            return try {
                (Class.forName("android.engineer.OppoEngineerManager").getMethod("fastbootUnlock", ByteArray::class.java, Integer.TYPE).invoke(null, bArr, 1) as Boolean)
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}