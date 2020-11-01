package com.curtisy.oppounlocker.heytap.manager

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.heytap.Constants
import com.curtisy.oppounlocker.heytap.parser.JsonConfigParser


class ConfigManager private constructor() {
    private val isPrepared = false
    var mEditor: SharedPreferences.Editor? = null
    private var mSharedPref: SharedPreferences? = null

    fun init(context: Context, str: String?) {
        var strVar = str
        if (TextUtils.isEmpty(strVar)) {
            strVar = CONFIG_PREFS_NAME
        }
        mSharedPref = context.getSharedPreferences(strVar, 0)
        mEditor = mSharedPref!!.edit()
        JsonConfigParser().parse(Constants.DEFAULT_CONFIG)
    }

    fun init(context: Context) {
        init(context, null)
    }

    fun commit() {
        mEditor!!.commit()
    }

    fun clear() {
        mEditor!!.clear().apply()
    }

    fun setIntData(str: String?, i: Int) {
        mEditor!!.putInt(str, i)
        mEditor!!.apply()
    }

    fun getIntData(str: String?, i: Int): Int {
        return mSharedPref!!.getInt(str, i)
    }

    fun getIntData(str: String?): Int {
        return mSharedPref!!.getInt(str, 0)
    }

    fun setFloatData(str: String?, f: Float) {
        mEditor!!.putFloat(str, f)
        mEditor!!.apply()
    }

    fun getFloatData(str: String?, f: Float): Float {
        return mSharedPref!!.getFloat(str, f)
    }

    fun getFloatData(str: String?): Float {
        return mSharedPref!!.getFloat(str, 0.0f)
    }

    fun setLongData(str: String?, l: Long) {
        mEditor!!.putLong(str, l)
        mEditor!!.apply()
    }

    fun getLongData(str: String?, l: Long): Long {
        return java.lang.Long.valueOf(mSharedPref!!.getLong(str, l))
    }

    fun getLongData(str: String?): Long {
        return java.lang.Long.valueOf(mSharedPref!!.getLong(str, 0))
    }

    fun setBooleanData(str: String?, z: Boolean) {
        mEditor!!.putBoolean(str, z)
        mEditor!!.apply()
    }

    fun getBooleanData(str: String?, z: Boolean): Boolean {
        return mSharedPref!!.getBoolean(str, z)
    }

    fun getBooleanData(str: String?): Boolean {
        return mSharedPref!!.getBoolean(str, false)
    }

    fun setStringData(str: String?, str2: String?) {
        mEditor!!.putString(str, str2)
        mEditor!!.apply()
    }

    fun getStringData(str: String?, str2: String?): String? {
        return mSharedPref!!.getString(str, str2)
    }

    fun getStringData(str: String?): String? {
        return mSharedPref!!.getString(str, BuildConfig.FLAVOR)
    }

    fun setStringSet(str: String?, hashSet: HashSet<String>?) {
        mEditor!!.putStringSet(str, hashSet)
        mEditor!!.apply()
    }

    fun getStringSet(str: String?, hashSet: HashSet<String?>?): HashSet<String>? {
        return mSharedPref!!.getStringSet(str, hashSet) as HashSet?
    }

    fun getListData(str: String?): ArrayList<String> {
        val stringSet = getStringSet(str, HashSet())
        val arrayList = ArrayList<String>()
        val it: Iterator<String> = stringSet!!.iterator()
        while (it.hasNext()) {
            arrayList.add(it.next())
        }
        return arrayList
    }

    fun setListData(str: String?, list: List<String>?) {
        if (list != null) {
            val hashSet: HashSet<String> = HashSet()
            for (str2 in list) {
                hashSet.add(str2)
            }
            setStringSet(str, hashSet)
        }
    }

    companion object {
        private const val CONFIG_PREFS_NAME = "com.coloros.net"
        private val LOCK = ByteArray(0)
        val instance = ConfigManager()
    }
}