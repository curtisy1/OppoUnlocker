package com.curtisy.oppounlocker.service

import android.content.Intent
import android.os.Handler
import android.os.Message
import android.util.Log
import com.curtisy.oppounlocker.MainActivity
import com.curtisy.oppounlocker.utilities.Utils


internal class MainActivityHandler(val mainActivity: MainActivity) : Handler() {
    override fun handleMessage(message: Message) {
        when (mainActivity.f6771m) {
            11 -> {
                val intent = Intent(mainActivity, StatusActivity::class.java)
                intent.putExtra("resultCode", message.what)
                intent.putExtra("data", message.obj as String)
                mainActivity.startActivity(intent)
                return
            }
            12 -> {
                val i: Int = message.what
                if (i == 100) {
                    Log.i("MainActivityHandler", "No network connection")
                    return
                } else if (i != 200) {
                    Log.i("MainActivityHandler", "Invalid login")
                    return
                } else {
                    Utils.fastbootUnlock()
                    mainActivity.f6770l = -1
                    mainActivity.onStart()
                    return
                }
            }
            else -> {
                val i2: Int = message.what
                if (i2 == 100) {
                    Log.i("MainActivityHandler", "No network connection")
                    return
                } else if (i2 != 200) {
                    Log.i("MainActivityHandler", "Invalid login")
                    return
                } else if ((message.obj as Int).toInt() == 0) {
                    mainActivity.f6770l = -2
                    mainActivity.onStart()
                    return
                } else {
                    mainActivity.f6770l = -1
                    mainActivity.onStart()
                    return
                }
            }
        }
    }
}