package com.curtisy.oppounlocker.heytap.parser

import android.text.TextUtils
import com.curtisy.oppounlocker.heytap.Constants
import com.curtisy.oppounlocker.heytap.manager.ConfigManager
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class JsonConfigParser {
    // neton.client.config.Parser
    fun parse(str: String) {
        try {
            val jSONObject = JSONObject(str)
            val string = jSONObject.getString(Constants.KEY_VERSION)
            if (!TextUtils.isEmpty(string) && string.compareTo(ConfigManager.instance.getStringData(Constants.KEY_VERSION)!!) > 0) {
                if (jSONObject.has(Constants.KEY_ENCYPT_VERSION)) {
                    ConfigManager.instance.setStringData(Constants.KEY_ENCYPT_VERSION, jSONObject.getString(Constants.KEY_ENCYPT_VERSION))
                }
                if (jSONObject.has(Constants.KEY_ENCYPT_SECRET)) {
                    ConfigManager.instance.setStringData(Constants.KEY_ENCYPT_SECRET, jSONObject.getString(Constants.KEY_ENCYPT_SECRET))
                }
                if (jSONObject.has(Constants.KEY_TRACE_HIT)) {
                    ConfigManager.instance.setIntData(Constants.KEY_TRACE_HIT, jSONObject.getString(Constants.KEY_TRACE_HIT).toInt())
                }
                if (jSONObject.has(Constants.KEY_DNS_MODE)) {
                    ConfigManager.instance.setIntData(Constants.KEY_DNS_MODE, jSONObject.getString(Constants.KEY_DNS_MODE).toInt())
                }
                if (jSONObject.has(Constants.KEY_SESSION_TIMEOUT)) {
                    ConfigManager.instance.setIntData(Constants.KEY_SESSION_TIMEOUT, jSONObject.getString(Constants.KEY_SESSION_TIMEOUT).toInt())
                }
                if (jSONObject.has(Constants.KEY_SESSION_CACHE_SIZE)) {
                    ConfigManager.instance.setIntData(Constants.KEY_SESSION_CACHE_SIZE, jSONObject.getString(Constants.KEY_SESSION_CACHE_SIZE).toInt())
                }
                if (jSONObject.has(Constants.KEY_RETRY_TIMES)) {
                    ConfigManager.instance.setIntData(Constants.KEY_RETRY_TIMES, jSONObject.getInt(Constants.KEY_RETRY_TIMES))
                }
                if (jSONObject.has(Constants.KEY_RETRY_INTERVAL_TIME)) {
                    ConfigManager.instance.setLongData(Constants.KEY_RETRY_INTERVAL_TIME, java.lang.Long.valueOf(jSONObject.getLong(Constants.KEY_RETRY_INTERVAL_TIME)))
                }
                if (jSONObject.has(Constants.KEY_LIVE_ON_TIME)) {
                    ConfigManager.instance.setLongData(Constants.KEY_LIVE_ON_TIME, java.lang.Long.valueOf(jSONObject.getLong(Constants.KEY_LIVE_ON_TIME)))
                }
                if (jSONObject.has(Constants.KEY_TRACE_URL)) {
                    ConfigManager.instance.setStringData(Constants.KEY_TRACE_URL, jSONObject.getString(Constants.KEY_TRACE_URL))
                }
                if (jSONObject.has(Constants.KEY_FOREIGN_TRACE_URL)) {
                    ConfigManager.instance.setStringData(Constants.KEY_FOREIGN_TRACE_URL, jSONObject.getString(Constants.KEY_FOREIGN_TRACE_URL))
                }
                if (jSONObject.has(Constants.KEY_HTTP_LAST_DNS)) {
                    ConfigManager.instance.setStringData(Constants.KEY_HTTP_LAST_DNS, jSONObject.getString(Constants.KEY_HTTP_LAST_DNS))
                }
                if (jSONObject.has(Constants.KEY_FOREIGN_HTTP_LAST_DNS)) {
                    ConfigManager.instance.setStringData(Constants.KEY_FOREIGN_HTTP_LAST_DNS, jSONObject.getString(Constants.KEY_FOREIGN_HTTP_LAST_DNS))
                }
                if (jSONObject.has(Constants.KEY_HTTP_DNS_LIST)) {
                    val string2 = jSONObject.getString(Constants.KEY_HTTP_DNS_LIST)
                    if (!TextUtils.isEmpty(string2)) {
                        ConfigManager.instance.setListData(Constants.KEY_HTTP_DNS_LIST, Arrays.asList(*string2.split(Constants.SPLITER).toTypedArray()))
                    }
                }
                if (jSONObject.has(Constants.KEY_FOREIGN_HTTP_DNS_LIST)) {
                    val string3 = jSONObject.getString(Constants.KEY_FOREIGN_HTTP_DNS_LIST)
                    if (!TextUtils.isEmpty(string3)) {
                        ConfigManager.instance.setListData(Constants.KEY_FOREIGN_HTTP_DNS_LIST, Arrays.asList(*string3.split(Constants.SPLITER).toTypedArray()))
                    }
                }
                if (jSONObject.has(Constants.KEY_VERSION)) {
                    ConfigManager.instance.setStringData(Constants.KEY_VERSION, string)
                }
                ConfigManager.instance.commit()
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        } catch (e2: Exception) {
            e2.printStackTrace()
        }
    }
}