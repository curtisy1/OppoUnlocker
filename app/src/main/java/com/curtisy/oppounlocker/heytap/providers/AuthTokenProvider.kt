package com.curtisy.oppounlocker.heytap.providers

import android.content.Context
import android.content.pm.PackageManager.NameNotFoundException
import android.database.Cursor
import android.net.Uri
import android.text.TextUtils
import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.helper.ApkInfoHelper.m6318a
import com.curtisy.oppounlocker.utilities.XORUtils


object AuthTokenProvider {
    /* renamed from: a */
    fun m6162a(context: Context): String {
        var cursor: Cursor? = null
        val context2: Context?
        if (m6170f(context)) {
            context2 = try {
                context.createPackageContext(XORUtils.hash("kge&gxxg&{mz~akm&ikkg}f|"), Context.CONTEXT_IGNORE_SECURITY)
            } catch (e: NameNotFoundException) {
                e.printStackTrace()
                null
            }
            return if (context2 != null) {
                context2.getSharedPreferences("USER_INFO", Context.MODE_MULTI_PROCESS).getString("USER_INFO_TOKEN", "") ?: ""
            } else ""
        }
        return try {
            cursor = context.contentResolver.query(Uri.parse(XORUtils.hash("kgf|mf|2''kge&gxxg&{mz~akm&ikkg}f|&|gcmfxzg~almz")), null, null, null, null)
            try {
                cursor!!.moveToFirst()
                val string = cursor.getString(0)
                try {
                    cursor.close()
                    string
                } catch (unused: Exception) {
                    string
                }
            } catch (unused2: Exception) {
                try {
                    cursor!!.close()
                } catch (unused3: Exception) {
                }
                ""
            } catch (th: Throwable) {
                try {
                    cursor!!.close()
                } catch (unused4: Exception) {
                }
                throw th
            }
        } catch (unused5: Exception) {
            cursor!!.close()
            ""
        } catch (th2: Throwable) {
            cursor!!.close()
            throw th2
        }
    }

    /* renamed from: b */
    fun m6164b(context: Context): String? {
        var cursor: Cursor? = null
        val context2: Context?
        if (m6170f(context)) {
            context2 = try {
                context.createPackageContext(XORUtils.hash("kge&gxxg&{mz~akm&ikkg}f|"), Context.CONTEXT_IGNORE_SECURITY)
            } catch (e: NameNotFoundException) {
                e.printStackTrace()
                null
            }
            return if (context2 != null) {
                context2.getSharedPreferences("USER_INFO", Context.MODE_MULTI_PROCESS).getString("USER_INFO_UNAME", "")
            } else ""
        }
        return try {
            cursor = context.contentResolver.query(Uri.parse(XORUtils.hash("kgf|mf|2''kge&gxxg&{mz~akm&ikkg}f|&|gcmfxzg~almz")), null, null, null, null)
            try {
                cursor!!.moveToFirst()
                val string = cursor.getString(1)
                try {
                    cursor.close()
                    string
                } catch (unused: Exception) {
                    string
                }
            } catch (unused2: Exception) {
                try {
                    cursor!!.close()
                } catch (unused3: Exception) {
                }
                ""
            } catch (th: Throwable) {
                try {
                    cursor!!.close()
                } catch (unused4: Exception) {
                }
                throw th
            }
        } catch (unused5: Exception) {
            cursor!!.close()
            ""
        } catch (th2: Throwable) {
            cursor!!.close()
            throw th2
        }
    }

    /* renamed from: c */
    fun m6166c(context: Context): String {
        var cursor: Cursor? = null
        return try {
            cursor = context.contentResolver.query(Uri.parse(XORUtils.hash("kgf|mf|2''kge&gxxg&}{mzkmf|mz&ikkg}f|afng")), null, null, null, null)
            try {
                cursor!!.moveToFirst()
                val string = cursor.getString(1)
                when {
                    TextUtils.isEmpty(string) -> {
                        try {
                            cursor.close()
                        } catch (unused: Exception) {
                        }
                        "0"
                    }
                    string == "-1" -> {
                        try {
                            cursor.close()
                        } catch (unused2: Exception) {
                        }
                        "0"
                    }
                    else -> {
                        try {
                            cursor.close()
                        } catch (unused3: Exception) {
                        }
                        string
                    }
                }
            } catch (unused4: Exception) {
                try {
                    cursor!!.close()
                } catch (unused5: Exception) {
                }
                "0"
            } catch (th2: Throwable) {
                try {
                    cursor!!.close()
                } catch (unused6: Exception) {
                }
                throw th2
            }
        } catch (unused7: Exception) {
            cursor!!.close()
            "0"
        } catch (th3: Throwable) {
            cursor!!.close()
            throw th3
        }
    }

    /* renamed from: d */
    fun m6168d(context: Context): String {
        var cursor: Cursor? = null
        return try {
            cursor = context.contentResolver.query(Uri.parse(XORUtils.hash("kgf|mf|2''kge&gxxg&}{mzkmf|mz&ikkg}f|afng")), null, null, null, null)
            try {
                cursor!!.moveToFirst()
                val string = cursor.getString(0)
                if (TextUtils.isEmpty(string)) {
                    try {
                        cursor.close()
                    } catch (unused: Exception) {
                    }
                    return ""
                }
                try {
                    cursor.close()
                } catch (unused2: Exception) {
                }
                string
            } catch (unused3: Exception) {
                try {
                    cursor!!.close()
                } catch (unused4: Exception) {
                }
                ""
            } catch (th: Throwable) {
                try {
                    cursor!!.close()
                } catch (unused5: Exception) {
                }
                throw th
            }
        } catch (unused6: Exception) {
            cursor!!.close()
            ""
        } catch (th2: Throwable) {
            cursor!!.close()
            throw th2
        }
    }

    /* renamed from: a */
    fun m6163a(context: Context, str: String?): String {
        val string: String
        if (!m6169e(context) || !m6167c(context, str)) {
            try {
                val createPackageContext = context.createPackageContext(XORUtils.hash("kge&gxxg&{mz~akm&ikkg}f|"), Context.CONTEXT_IGNORE_SECURITY)
                        ?: return ""
                string = createPackageContext.getSharedPreferences("USER_TOKEN_INFO", Context.MODE_MULTI_PROCESS).getString(str, "") ?: ""
            } catch (e: NameNotFoundException) {
                e.printStackTrace()
                return ""
            }
        } else {
            try {
                val createPackageContext2 = context.createPackageContext(XORUtils.hash("kge&gxxg&{mz~akm&ikkg}f|"), Context.CONTEXT_IGNORE_SECURITY)
                        ?: return ""
                string = createPackageContext2.getSharedPreferences("APP_LOGIN_RECORD_INFO", Context.MODE_MULTI_PROCESS).getString("TokenWhenOneAccount", "") ?: ""
            } catch (e2: NameNotFoundException) {
                e2.printStackTrace()
                return ""
            }
        }
        return string
    }

    /* renamed from: b */
    fun m6165b(context: Context, str: String?): String? {
        val string: String
        if (!m6169e(context) || !m6167c(context, str)) {
            try {
                val createPackageContext = context.createPackageContext(XORUtils.hash("kge&gxxg&{mz~akm&ikkg}f|"), Context.CONTEXT_IGNORE_SECURITY)
                        ?: return ""
                string = createPackageContext.getSharedPreferences("USER_NAME_INFO", Context.MODE_MULTI_PROCESS).getString(str, "") ?: ""
            } catch (e: NameNotFoundException) {
                e.printStackTrace()
                return ""
            }
        } else {
            try {
                val createPackageContext2 = context.createPackageContext(XORUtils.hash("kge&gxxg&{mz~akm&ikkg}f|"), Context.CONTEXT_IGNORE_SECURITY)
                        ?: return ""
                string = createPackageContext2.getSharedPreferences("APP_LOGIN_RECORD_INFO", Context.MODE_MULTI_PROCESS).getString("NameWhenOneAccount", "") ?: ""
            } catch (e2: NameNotFoundException) {
                e2.printStackTrace()
                return ""
            }
        }
        return string
    }

    /* renamed from: f */
    private fun m6170f(context: Context): Boolean {
        val i: Int
        i = try {
            m6318a(context, XORUtils.hash("kge&gxxg&{mz~akm&ikkg}f|"))
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
        return i >= 210
    }

    /* renamed from: e */
    fun m6169e(context: Context): Boolean {
        return try {
            val createPackageContext = context.createPackageContext(XORUtils.hash("kge&gxxg&{mz~akm&ikkg}f|"), Context.CONTEXT_IGNORE_SECURITY)
            createPackageContext?.getSharedPreferences("APP_LOGIN_RECORD_INFO", Context.MODE_MULTI_PROCESS)?.getBoolean("IsOneAccount", false)
                    ?: false
        } catch (e: NameNotFoundException) {
            e.printStackTrace()
            false
        }
    }

    /* renamed from: c */
    fun m6167c(context: Context, str: String?): Boolean {
        return try {
            val createPackageContext = context.createPackageContext(XORUtils.hash("kge&gxxg&{mz~akm&ikkg}f|"), Context.CONTEXT_IGNORE_SECURITY)
            createPackageContext?.getSharedPreferences("APP_LOGIN_RECORD_INFO", Context.MODE_MULTI_PROCESS)?.getBoolean(str, true)
                    ?: true
        } catch (e: NameNotFoundException) {
            e.printStackTrace()
            true
        }
    }
}