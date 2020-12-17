package com.curtisy.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import com.curtisy.myapplication.databinding.ActivityMainBinding
import java.security.KeyFactory
import java.security.Signature
import java.security.spec.X509EncodedKeySpec


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // This is the original method that gets called by the depth testing apk to handle an unlock
        // However as a non-system app, we can't access this method so we need to look at alternatives
        // You can use jadx to decompile pretty much any Oppo app as they aren't obfuscated
        // the only callable ones are in the android package so far
        //invokeOppoAppMethod("android.engineer", "OppoEngineerManager", "fastbootUnlock")
        invokeOppoAppMethod("android.app", "OppoWhiteListManager", "getGlobalWhiteList")
    }

    fun invokeOppoAppMethod(packageName: String, className: String, methodName: String): Boolean {
        val bArr = byteArrayOf(0)
        return try {
            val packagePath = "android.app"
            val engineerManager = Class.forName("$packageName.$className")
            val methods = engineerManager.declaredMethods
            val ctor = engineerManager.getConstructor(Context::class.java)
            val instance = ctor.newInstance(applicationContext)
            val whiteList = instance.javaClass.getMethod(methodName).invoke(instance)
            val fastBootUnlockMethod = engineerManager.getDeclaredMethod("fastbootUnlock", ByteArray::class.java, Integer::class.java)
            ////fastBootUnlockMethod.invoke(null, bArr, 1)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    // TODO: Fix this for fun
    fun verifiySignature() {
//        val sig = Signature.getInstance("SHA256withRSA")
//        sig.initVerify(KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(Base64.decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw8nyzqm7wJJQ/8CTTMiycJoCUg8ogYWf445dLBbPdzGG9qLCCABvROhkk34dSbk8dqkzZcyzbfs7dCoB3U2LVkWCGZvsSCvmP21CL8JU2WCupe4YxRTCpbd22YQMvjvBcbNw4v187Jy0QqtPQqTiYJKALma+9WzGJnkAqYfu93UK7mgqy07d4qkn8ELVWNSI7+VO83CCE6GtpaZPVWkGmkCwLXtiEEmBQ+SlQPKf/xXR4MxVHR1thvpYy+XP9NBcnrzeuF3V6mqvZfXLt4d/sSDz5X/qFnLuI6tVLm4GjMhF32nL/+MSkU4AaXjvUYsgBRPYuh1csuamd0/ud5aUbwIDAQAB", 0))))
//        sig.update((getSerialno() + getRandomNumStr()).getBytes())
//        val result: Boolean = sig.verify(Base64.decode(signature, 0))
    }
}