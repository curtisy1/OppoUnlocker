package com.curtisy.oppounlocker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.curtisy.oppounlocker.utilities.AesEncryptUtils
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val modelValue = findViewById<TextView>(R.id.model_value)
        val languageValue = findViewById<TextView>(R.id.language_value)
        val otaVersionValue = findViewById<TextView>(R.id.ota_version_value)
        val keyValue = findViewById<TextView>(R.id.key_value)

        modelValue.text = "PDEM30"
        otaVersionValue.text = "PDEM10_11.A.17_0470_202009091604"
        languageValue.text = Locale.getDefault().toLanguageTag()
        keyValue.text = AesEncryptUtils.m6011a(this)
    }
}