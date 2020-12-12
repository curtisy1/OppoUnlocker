package com.curtisy.oppounlocker.heytap

import android.content.Context
import android.content.ServiceConnection
import android.os.Handler
import android.os.Message
import android.util.Log
import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.heytap.models.C1259g
import com.curtisy.oppounlocker.heytap.models.C1260h
import com.curtisy.oppounlocker.heytap.models.UserEntity


class CallInfoAgent(context: Context?) {
    /* renamed from: a */
    private var f6890a: Context? = null

    /* renamed from: b */
    val f6891b = 99999

    /* renamed from: d */
    private val f6892d: AbstractBinderC1272h = BinderC1256d(this)

    /* renamed from: f */
    private var f6893f: C1260h? = null

    /* renamed from: g */
    private val f6894g: ServiceConnection = ServiceConnectionC1257e(this)

    /* renamed from: i */
    private var f6895i: C1259g? = null

    /* renamed from: j */
    private val f6896j: ServiceConnection = ServiceConnectionC1258f(this)

    /* access modifiers changed from: protected */ /* renamed from: a */
    fun mo6892a(handler: Handler?) {
        Log.e("reqToken", "currentHandler=" + f6887c)
        if (f6887c == null) {
            f6887c = handler
            m6182l()
            f6893f = C1260h(this, 1)
            f6893f!!.start()
            return
        }
        m6180j()
    }

    /* access modifiers changed from: protected */ /* renamed from: b */
    fun mo6896b(handler: Handler?) {
        if (f6887c == null) {
            f6887c = handler
            m6182l()
            f6893f = C1260h(this, 2)
            f6893f!!.start()
            return
        }
        m6180j()
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:?, code lost:
        mo6891a();
        r4.f6890a.bindService(r0, r4.f6894g, 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0027, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0028, code lost:
        mo6891a();
        m6181k();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002e, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */ /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x001d */ /* renamed from: l */ /* Code decompiled incorrectly, please refer to instructions dump. */
    private fun m6182l() {
        /*
            r4 = this;
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "kge&fmizem&{mz~mz&gxxg{mz~akm"
            java.lang.String r1 = com.platform.usercenter.p065c.UCHeyTapCommonProvider.m6288a(r1)
            r0.<init>(r1)
            java.lang.String r1 = "kge&gxxg&{mz~akm&ikkg}f|"
            java.lang.String r1 = com.platform.usercenter.p065c.UCHeyTapCommonProvider.m6288a(r1)
            r0.setPackage(r1)
            r1 = 1
            android.content.Context r2 = r4.f6890a     // Catch:{ Exception -> 0x001d }
            android.content.ServiceConnection r3 = r4.f6894g     // Catch:{ Exception -> 0x001d }
            r2.bindService(r0, r3, r1)     // Catch:{ Exception -> 0x001d }
            return
        L_0x001d:
            r4.mo6891a()     // Catch:{ Exception -> 0x0028 }
            android.content.Context r2 = r4.f6890a     // Catch:{ Exception -> 0x0028 }
            android.content.ServiceConnection r3 = r4.f6894g     // Catch:{ Exception -> 0x0028 }
            r2.bindService(r0, r3, r1)     // Catch:{ Exception -> 0x0028 }
            return
        L_0x0028:
            r4.mo6891a()
            m6181k()
            return
        */
        throw UnsupportedOperationException("Method not decompiled: com.heytap.p061a.p062a.CallInfoAgent.m6182l():void")
    }

    /* renamed from: a */
    fun mo6891a() {
        if (f6888e != null) {
            try {
                f6888e.mo6996b(f6892d)
                f6890a!!.unbindService(f6894g)
                f6893f.interrupt()
                f6893f = null
            } catch (unused: Exception) {
                m6181k()
            }
        }
    }

    /* access modifiers changed from: protected */ /* renamed from: b */
    fun mo6894b(): UserEntity? {
        return try {
            f6888e.mo6994a(f6892d)
            f6888e.mo6993a(m6174a(f6890a))
        } catch (unused: Exception) {
            m6181k()
            null
        }
    }

    /* access modifiers changed from: protected */ /* renamed from: c */
    fun mo6898c(): UserEntity? {
        return try {
            f6888e.mo6994a(f6892d)
            f6888e.mo6995b(m6174a(f6890a))
        } catch (unused: Exception) {
            m6181k()
            null
        }
    }

    /* access modifiers changed from: protected */ /* renamed from: d */
    fun mo6900d(): UserEntity? {
        return try {
            f6888e.mo6994a(f6892d)
            f6888e.mo6997c(m6174a(f6890a))
        } catch (unused: Exception) {
            m6181k()
            null
        }
    }

    /* access modifiers changed from: protected */ /* renamed from: a */
    fun mo6893a(handler: Handler, str: String) {
        if (f6887c == null) {
            f6887c = handler
            m6183m()
            f6895i = C1259g(this, 1, str)
            f6895i!!.start()
            return
        }
        m6180j()
    }

    /* access modifiers changed from: protected */ /* renamed from: b */
    fun mo6897b(handler: Handler?, str: String?) {
        if (f6887c == null) {
            f6887c = handler
            m6183m()
            f6895i = C1259g(this, 2, str)
            f6895i.start()
            return
        }
        m6180j()
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:?, code lost:
        mo6901e();
        r4.f6890a.bindService(r0, r4.f6896j, 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0027, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0028, code lost:
        mo6901e();
        m6181k();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002e, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */ /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x001d */ /* renamed from: m */ /* Code decompiled incorrectly, please refer to instructions dump. */
    private fun m6183m() {
        /*
            r4 = this;
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "kge&fmizem&{mz~mz&gxxg{mz~akmjqixxkglm"
            java.lang.String r1 = com.platform.usercenter.p065c.UCHeyTapCommonProvider.m6288a(r1)
            r0.<init>(r1)
            java.lang.String r1 = "kge&gxxg&{mz~akm&ikkg}f|"
            java.lang.String r1 = com.platform.usercenter.p065c.UCHeyTapCommonProvider.m6288a(r1)
            r0.setPackage(r1)
            r1 = 1
            android.content.Context r2 = r4.f6890a     // Catch:{ Exception -> 0x001d }
            android.content.ServiceConnection r3 = r4.f6896j     // Catch:{ Exception -> 0x001d }
            r2.bindService(r0, r3, r1)     // Catch:{ Exception -> 0x001d }
            return
        L_0x001d:
            r4.mo6901e()     // Catch:{ Exception -> 0x0028 }
            android.content.Context r2 = r4.f6890a     // Catch:{ Exception -> 0x0028 }
            android.content.ServiceConnection r3 = r4.f6896j     // Catch:{ Exception -> 0x0028 }
            r2.bindService(r0, r3, r1)     // Catch:{ Exception -> 0x0028 }
            return
        L_0x0028:
            r4.mo6901e()
            m6181k()
            return
        */
        throw UnsupportedOperationException("Method not decompiled: com.heytap.p061a.p062a.CallInfoAgent.m6183m():void")
    }

    /* access modifiers changed from: protected */ /* renamed from: e */
    fun mo6901e() {
        if (f6889h != null) {
            try {
                f6889h.mo7003b(f6892d)
                f6890a!!.unbindService(f6896j)
                f6895i.interrupt()
                f6895i = null
            } catch (unused: Exception) {
                m6181k()
            }
        }
    }

    /* access modifiers changed from: protected */ /* renamed from: a */
    fun mo6890a(str: String?): UserEntity? {
        try {
            f6889h.mo7001a(f6892d)
        } catch (e: Exception) {
            try {
                e.printStackTrace()
            } catch (unused: Exception) {
                m6181k()
                return null
            }
        }
        return f6889h.mo7000a(m6174a(f6890a), str)
    }

    /* access modifiers changed from: protected */ /* renamed from: b */
    fun mo6895b(str: String?): UserEntity? {
        return try {
            f6889h.mo7001a(f6892d)
            f6889h.mo7002b(m6174a(f6890a), str)
        } catch (unused: Exception) {
            m6181k()
            null
        }
    }

    /* access modifiers changed from: protected */ /* renamed from: c */
    fun mo6899c(str: String?): UserEntity? {
        return try {
            f6889h.mo7001a(f6892d)
            f6889h.mo7004c(m6174a(f6890a), str)
        } catch (unused: Exception) {
            m6181k()
            null
        }
    }

    companion object {
        /* renamed from: c */
        var f6887c: Handler?

        /* renamed from: e */
        var f6888e: IAskToken? = null

        /* renamed from: h */
        var f6889h: IAskTokenByAppCode? = null

        /* renamed from: a */
        private fun m6174a(context: Context?): String {
            return try {
                val packageName = context!!.packageName
                if (packageName == null || packageName == "null") BuildConfig.FLAVOR else packageName
            } catch (unused: Exception) {
                BuildConfig.FLAVOR
            }
        }

        /* renamed from: j */
        private fun m6180j() {
            val message = Message()
            message.obj = UserEntity(
                Constants.REQ_OCCUPY,
                "Occupied error!",
                BuildConfig.FLAVOR,
                BuildConfig.FLAVOR
            )
            val handler: Handler? = f6887c
            if (!(handler == null || f6887c == null)) {
                handler.sendMessage(message)
            }
            f6887c = null
        }

        /* renamed from: k */
        private fun m6181k() {
            val message = Message()
            message.obj = UserEntity(
                Constants.REQ_EXCEPTION,
                "Exception error!",
                BuildConfig.FLAVOR,
                BuildConfig.FLAVOR
            )
            val handler: Handler? = f6887c
            if (!(handler == null || f6887c == null)) {
                handler.sendMessage(message)
            }
            f6887c = null
        }
    }

    init {
        f6890a = context
        val message = Message()
        message.obj = UserEntity(
            Constants.REQ_CANCLE,
            "Already canceled!",
            BuildConfig.FLAVOR,
            BuildConfig.FLAVOR
        )
        val handler: Handler? = f6887c
        if (!(handler == null || f6887c == null)) {
            handler.sendMessage(message)
        }
        f6887c = null
        f6887c = null
    }
}