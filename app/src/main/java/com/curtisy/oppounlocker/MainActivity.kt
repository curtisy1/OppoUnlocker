package com.curtisy.oppounlocker

import android.R
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.curtisy.oppounlocker.heytap.helper.UserCenterHelper.m6036a
import com.curtisy.oppounlocker.heytap.helper.UserCenterHelper.m6037a
import com.curtisy.oppounlocker.service.MainActivityHandler
import com.curtisy.oppounlocker.service.ServiceHandler
import com.curtisy.oppounlocker.utilities.AesEncryptUtils
import com.curtisy.oppounlocker.utilities.Utils
import java.util.*


class MainActivity : AppCompatActivity() {
    var f6768j = false

    /* renamed from: k */
    private var f6769k = false

    /* renamed from: l */
    var f6770l = 99

    /* renamed from: m */
    var f6771m = 10

    /* renamed from: n */
    private var f6772n: Context? = null

    /* renamed from: o */
    private var f6773o: Button? = null

    /* renamed from: p */
    private var f6774p: TextView? = null

    /* renamed from: r */
    private val f6776r: Handler = MainActivityHandler(this)

    /* access modifiers changed from: protected */
    // androidx.activity.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    override fun onCreate(bundle: Bundle?) {
        // TODO: This is taken from the closed source app
        // Not sure yet, what to do with it
//        super.onCreate(bundle)
//        requestWindowFeature(1)
//        setContentView(R.layout.activity_main)
//        val window = window
//        window.addFlags(Int.MIN_VALUE)
//        window.navigationBarColor = -1
//        window.statusBarColor = 0
//        window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or 8192
//        f6772n = applicationContext
//        if (m6037a(f6772n)) {
//            f6769k = true
//        }
//        m6069i()
//        f6773o = findViewById<View>(R.id.apply) as Button
//        f6773o!!.setOnClickListener(`View$OnClickListenerC1223h`(this))
//        f6774p = findViewById<View>(R.id.status) as TextView
//        f6774p!!.setOnClickListener(`View$OnClickListenerC1224i`(this))

        super.onCreate(bundle)
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

    /* access modifiers changed from: protected */
    // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public override fun onStart() {
        super.onStart()
        if (f6770l == 1 && m6037a(f6772n!!) && !f6769k) {
            m6069i()
        }
    }

    /* access modifiers changed from: private */ /* renamed from: i */
    fun m6069i() {
        f6770l = Utils.m6046b(this)
        if (f6770l == 0) {
            return
        }
        if (!m6037a(f6772n!!)) {
            m6036a(f6772n!!, Handler(Looper.getMainLooper()))
        } else if (Utils.m6044a(this)) {
            Utils.startRequestService(this, 1003, f6776r)
            f6771m = 10
        }
    }

    companion object {
        var f6768j = false
    }
}