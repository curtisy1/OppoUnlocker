package com.curtisy.oppounlocker.utilities

import android.text.TextUtils
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

class Utils {
    companion object{
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
    }
}