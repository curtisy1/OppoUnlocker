package com.curtisy.oppounlocker.utilities

import android.content.Context
import android.text.TextUtils
import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.heytap.NetonClient
import com.curtisy.oppounlocker.heytap.http.Request
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
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
                NetonClient.execute(Request.Builder()
                        .url(str)
                        .addHeader("model", "PDEM30")
                        .addHeader("otaVersion", "PDEM10_11.A.17_0470_202009091604")
                        .addHeader("language", Locale.getDefault().toLanguageTag())
                        .addHeader("key", AesEncryptUtils.m6011a(context!!))
                        .post(str2.toRequestBody("JSON".toMediaTypeOrNull()))
                        .build())
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}