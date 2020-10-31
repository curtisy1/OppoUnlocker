package com.curtisy.oppounlocker.heytap

import java.nio.charset.Charset

object Constants {
    const val EXTRA_ACTION_ACCOUNT_NAME_KEY = "activity_extra_key_username"
    const val EXTRA_ACTION_APPINFO_KEY = "extra_action_appinfo_key"
    const val EXTRA_ACTION_AUTO_LOGIN_KEY = "extra_action_auto_login_key"
    const val EXTRA_BROADCAST_ACTION_USERENTITY_KEY = "extra_broadcast_action_userentity_key"
    const val EXTRA_BROADCAST_ACTION_USERENTITY_KEY_NEEDCALLBACK = "extra_broadcast_action_userentity_key_need_callback"
    const val EXTRA_BROADCAST_MODIFY_NEW_USERNAME = "UserName"
    const val EXTRA_BROADCAST_MODIFY_OLD_USERNAME = "OldUserName"
    const val EXTRA_KEY_USERCENTER_PLUGIN_KEY = "extra_key_usercenter_plugin_key"
    const val EXTRA_MODIFY_RESULT = "EXTRA_MODIFY_RESULT"
    const val EXTRA_REQUEST_TYPE_KEY = "extra_request_type_key"
    const val KEY_MODIFY_FULLNAME_ACCOUNT_COUNTRY = "MODIFY_FULLNAME_ACCOUNT_COUNTRY"
    const val KEY_MODIFY_FULLNAME_FIRST_NAME = "MODIFY_FULLNAME_FIRST_NAME"
    const val KEY_MODIFY_FULLNAME_LAST_NAME = "MODIFY_FULLNAME_LAST_NAME"
    const val MSG_RESULT_FOR_REQ_BINDINFO = 40001000
    const val REQUESTCODE_USERCENTER_BINDINFO = 171
    const val REQUEST_CODE_MODIFY_ACCOUNTNAME = 582
    const val REQUEST_CODE_MODIFY_FULLNAME = 113
    const val REQUSET_TYPE_REQRESIGNIN = 48059

    @Deprecated("")
    val REQUSET_TYPE_REQSWITCH_ACCOUNT = 52428
    const val REQUSET_TYPE_REQTOKEN = 43690
    const val REQ_AIDL_TIMEOUT = 30001003
    const val REQ_APK_NOT_EXIST = 30001007
    const val REQ_CANCLE = 30001004
    const val REQ_EXCEPTION = 30001006
    const val REQ_FAILED = 30001002
    const val REQ_LOW_VERSION_SDK = 30003041
    const val REQ_NONE_ACCOUNT = 30003042
    const val REQ_NO_SUPPORT_ACCOUNT = 30003044
    const val REQ_NO_SUPPORT_ACCOUNTNAME = 30003045
    const val REQ_OCCUPY = 30001005
    const val REQ_PARAM_ERROR = 30001102
    const val REQ_SERVICE_NOT_EXIST = 30001101
    const val REQ_SUCCESS = 30001001
    const val REQ_TOKEN_EXCEPTION = 30003046
    const val REQ_TOKEN_NOT_EXIST = 30003040
    const val REQ_USERCENTER_NOT_EXIST = 30003043
    const val RESULT_CODE_MODIFY_ACCOUNTNAME = 23
    const val RESULT_CODE_MODIFY_FULLNAME = 24
    const val USERCENTER_PLUGIN_ID = 1002
    const val USERCENTER_SP_SUFFIX = "_suffix_usercenter_sharepreference"

    const val ACTION_ROM_UPDATED = "oppo.intent.action.ROM_UPDATE_CONFIG_SUCCESS"
    const val DEFAULT_CONFIG = "{\n\"version\":\"1.0.3\",\n\"http_dns\":\"\",\n\"foreign_http_dns\":\"\",\n\"encypt_version\":\"1\",\n\"encypt_secret\":\"defalut.netton.client.secret\",\n\"trace_hit\":100000,\n\"session_timeout\":604800,\n\"session_cache_size\":0,\n\"dns_mode\":1,\n\"retry_times\":1,\n\"live_on_time\":600000,\n\"retry_interval_time\":0,\n\"trace_url\":\"\",\n\"foreign_trace_url\":\"\",\n\"http_last_dns\":\"\",\n\"foreign_http_last_dns\":\"\"\n}"
    const val DEFAULT_DNS_MODE = 1
    var DEFAULT_ENCYPT_SECRET: ByteArray = "netton.client.st".toByteArray(Charset.defaultCharset())
    var DEFAULT_ENCYPT_VERSION = "1"
    var DEFAULT_FOREIGN_HTTP_DNS_LIST = arrayOfNulls<String>(0)
    var DEFAULT_HTTP_DNS_LIST = arrayOfNulls<String>(0)
    const val DEFAULT_LIVE_ON_TIME: Long = 600000
    const val DEFAULT_RETRY_INTERVAL_TIME: Long = 0
    const val DEFAULT_RETRY_TIMES = 1
    const val DEFAULT_SESSION_CACHE_SIZE = 0
    const val DEFAULT_SESSION_TIMEOUT = 604800
    const val DEFAULT_TRACE_HIT = 100000
    var DEFAULT_VERSION = "1.0.0"
    const val KEY_CLINET_ID = "clientID"
    const val KEY_CLINET_ID_MODIFY_TIME = "clientID_modify_time"
    const val KEY_CONFIG_LIST = "ROM_UPDATE_CONFIG_LIST"
    const val KEY_DNS_MODE = "dns_mode"
    const val KEY_ENCRYPT_HEADER = "encrypt_header"
    const val KEY_ENCYPT_SECRET = "encypt_secret"
    const val KEY_ENCYPT_VERSION = "encypt_version"
    const val KEY_FOREIGN_HTTP_DNS_LIST = "foreign_http_dns"
    const val KEY_FOREIGN_HTTP_LAST_DNS = "foreign_http_last_dns"
    const val KEY_FOREIGN_TRACE_URL = "foreign_trace_url"
    const val KEY_HEADER = "header"
    const val KEY_HEADER_SET = "OPPO_SET"
    const val KEY_HTTP_DNS_LIST = "http_dns"
    const val KEY_HTTP_LAST_DNS = "http_last_dns"
    const val KEY_LAST_LIVE_ON_TIME = "last_live_on_time"
    const val KEY_LIVE_ON_TIME = "live_on_time"
    const val KEY_MODE = "mode"
    const val KEY_REGIONCODE = "regionCode"
    const val KEY_RETRY_INTERVAL_TIME = "retry_interval_time"
    const val KEY_RETRY_TIMES = "retry_times"
    const val KEY_SESSION_CACHE_SIZE = "session_cache_size"
    const val KEY_SESSION_TIMEOUT = "session_timeout"
    const val KEY_TRACE_HIT = "trace_hit"
    const val KEY_TRACE_URL = "trace_url"
    const val KEY_VERSION = "version"
    const val NETTON_STATUS = "netton_status"
    const val PROTOCOL_VERSION: Byte = 32
    const val ROMUPDATE_NETON_FILTER = "NETON_CONFIG"
    const val SPLITER = ";"
    const val TRACE_LEVEL_12 = "1.2"
}