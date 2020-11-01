package com.curtisy.oppounlocker.heytap.usercenter

import com.curtisy.oppounlocker.helper.ApkInfoHelper.m6320c
import com.curtisy.oppounlocker.heytap.BaseApp
import com.curtisy.oppounlocker.utilities.XORUtils


object UCHeyTapAccountProvider {
    const val ACTION_HEYTAP_ACCOUNT_LOGIN = "com.usercenter.action.receiver.account_login"
    const val ACTION_HEYTAP_ACCOUNT_LOGOUT = "com.heytap.usercenter.account_logout"
    const val EXTRA_BROADCAST_USERCENTER_AESCODER_KEY = "kge&gxxg&}{mzkmf|mz&im{kglmzWcmq"

    @Deprecated("")
    val EXTRA_REQUEST_BIND_MESSENGER_KEY = "kge&gxxg&}{mzkmf|mz&zmy}m{|WjaflWem{{mfomzWcmq"

    @Deprecated("")
    val EXTRA_RESULT_USERCENTER_BIND_INFO = "kge&gxxg&}{mzkmf|mz&jaflWafng"
    const val PROVIDER_EXP_RELEASE_URL_XOR8 = "`||x{2''a}kn&gxxgegjadm&kge'~=&8'}{mzafng'ji{ak"
    const val PROVIDER_INTF_USERCENTER_CONTAINER_ACTIVITY_XOR8 = "gxxg&}{mkmf|mz&af|mf|&ik|agf&gxmf&af|mznikm"
    const val PROVIDER_INTF_USERCENTER_HT_CONTAINER_ACTIVITY_XOR8 = "kge&}{mzkmf|mz&ik|agf&ik|a~a|q&gxmf&af|mznikm"
    const val PROVIDER_REALME_HOST_URL_XOR8 = "`||x{2''kdamf|%}k&zmidemegjadm&kge'~=&8'}{mzafng'ji{ak"
    const val PROVIDER_RELEASE_URL_XOR8 = "`||x{2''a}k&gxxgegjadm&kge'~=&8'}{mzafng'ji{ak"
    const val PROVIDER_TEST_URL_XOR8 = "`||x{2''a&egjadmixa&}kfm|m{|&ifqgd&kge'~=&8'}{mzafng'ji{ak"
    const val PROVIDER_URL_USERCENTER_HT_OPEN_XOR8 = "kgf|mf|2''kge&}{mzkmf|mz&i}|`gza|am{&xzg~almz&gxmf"
    const val PROVIDER_URL_USERCENTER_OP_OPEN_XOR8 = "kgf|mf|2''kge&gxxg&}{mzkmf|mz&xzg~almz&gxmf"

    @Deprecated("")
    val PROVIDER_USERCENTER_ACCOUNT_LOGIN_COMPONENT_SAFE_XOR8 = "gxxg&af|mf|&ik|agf&}{mzkmf|mz&IKKG]F\\WDGOAF"
    const val PROVIDER_USERCENTER_ACCOUNT_LOGIN_XOR8 = "kge&gxxg&}{mzkmf|mz&ikkg}f|Wdgoaf"

    @Deprecated("")
    val PROVIDER_USERCENTER_ACCOUNT_LOGOUT_COMPONENT_SAFE_XOR8 = "gxxg&af|mf|&ik|agf&}{mzkmf|mz&IKKG]F\\WDGOG]\\"
    const val PROVIDER_USERCENTER_ACCOUNT_LOGOUT_XOR8 = "kge&gxxg&}{mzkmf|mz&ikkg}f|Wdgog}|"

    @Deprecated("")
    val PROVIDER_USERCENTER_ACCOUNT_MODIFY_NAME_COMPONENT_SAFE_XOR8 = "gxxg&af|mf|&ik|agf&}{mzkmf|mz&EGLANQWFIEM"
    const val PROVIDER_USERCENTER_ACCOUNT_MODIFY_NAME_XOR8 = "kge&gxxg&}{mzkmf|mz&eglanqWfiem"

    @Deprecated("")
    val PROVIDER_USERCENTER_AUTOLOGIN_SERVICE_XOR8 = "gxxg&}{mkmf|mz&af|mf|&ik|agf&i}|gdgoafW{mz~akm"

    @Deprecated("")
    val PROVIDER_USERCENTER_BIND_PAGE_XOR8 = "gxxg&af|mf|&ik|agf&jaflafng"
    const val PROVIDER_USERCENTER_FIRSTIN_XOR8 = "gxxg&}{mkmf|mz&af|mf|&ik|agf&naz{|af"
    const val PROVIDER_USERCENTER_HT_FIRSTIN_XOR8 = "kge&}{mzkmf|mz&ik|agf&ik|a~a|q&naz{|af"

    @Deprecated("")
    val PROVIDER_USERCENTER_MODIFY_ACCOUNTNAME_XOR8 = "gxxg&}{mkmf|mz&af|mf|&ik|agf&eglanqWikkg}f|fiem"

    @Deprecated("")
    val PROVIDER_USERCENTER_MODIFY_FULLNAME_XOR8 = "gxxg&}{mkmf|mz&af|mf|&ik|agf&eglanqWn}ddfiem"
    val providerIntfUsercenterOpenContainerActivityXor8: String
        get() = if (m6320c(BaseApp.f7020a, XORUtils.hash("kge&`mq|ix&}{mzkmf|mz"))) {
            XORUtils.hash(PROVIDER_INTF_USERCENTER_HT_CONTAINER_ACTIVITY_XOR8)
        } else XORUtils.hash(PROVIDER_INTF_USERCENTER_CONTAINER_ACTIVITY_XOR8)

    val providerUrlUsercenterOpOpenXor8: String
        get() = if (m6320c(BaseApp.f7020a, XORUtils.hash("kge&`mq|ix&}{mzkmf|mz"))) {
            XORUtils.hash(PROVIDER_URL_USERCENTER_HT_OPEN_XOR8)
        } else XORUtils.hash(PROVIDER_URL_USERCENTER_OP_OPEN_XOR8)
    val providerUsercenterFirstinXor8: String
        get() = if (m6320c(BaseApp.f7020a, XORUtils.hash("kge&`mq|ix&}{mzkmf|mz"))) {
            XORUtils.hash(PROVIDER_USERCENTER_HT_FIRSTIN_XOR8)
        } else XORUtils.hash(PROVIDER_USERCENTER_FIRSTIN_XOR8)
    val providerRealmeHostUrlXor8: String
        get() = XORUtils.hash(PROVIDER_REALME_HOST_URL_XOR8)
    val providerExpReleaseUrlXor8: String
        get() = XORUtils.hash(PROVIDER_EXP_RELEASE_URL_XOR8)
    val providerReleaseUrlXor8: String
        get() = XORUtils.hash(PROVIDER_RELEASE_URL_XOR8)
    val providerTestUrlXor8: String
        get() = XORUtils.hash(PROVIDER_TEST_URL_XOR8)
    val providerUsercenterAccountModifyNameComponentSafeXor8: String
        get() = XORUtils.hash(PROVIDER_USERCENTER_ACCOUNT_MODIFY_NAME_COMPONENT_SAFE_XOR8)
    val providerUsercenterAccountLogoutComponentSafeXor8: String
        get() = XORUtils.hash(PROVIDER_USERCENTER_ACCOUNT_LOGOUT_COMPONENT_SAFE_XOR8)
    val providerUsercenterAccountLoginComponentSafeXor8: String
        get() = XORUtils.hash(PROVIDER_USERCENTER_ACCOUNT_LOGIN_COMPONENT_SAFE_XOR8)
    val providerUsercenterAccountLoginXor8: String
        get() = XORUtils.hash(PROVIDER_USERCENTER_ACCOUNT_LOGIN_XOR8)
    val providerUsercenterAccountLogoutXor8: String
        get() = XORUtils.hash(PROVIDER_USERCENTER_ACCOUNT_LOGOUT_XOR8)
    val providerUsercenterAccountModifyNameXor8: String
        get() = XORUtils.hash(PROVIDER_USERCENTER_ACCOUNT_MODIFY_NAME_XOR8)
    val extraBroadcastUsercenterAescoderKey: String
        get() = XORUtils.hash(EXTRA_BROADCAST_USERCENTER_AESCODER_KEY)
    val providerUsercenterBindPageXor8: String
        get() = XORUtils.hash(PROVIDER_USERCENTER_BIND_PAGE_XOR8)
    val extraRequestBindMessengerKey: String
        get() = XORUtils.hash(EXTRA_REQUEST_BIND_MESSENGER_KEY)
    val extraResultUsercenterBindInfo: String
        get() = XORUtils.hash(EXTRA_RESULT_USERCENTER_BIND_INFO)
    val providerUsercenterModifyFullnameXor8: String
        get() = XORUtils.hash(PROVIDER_USERCENTER_MODIFY_FULLNAME_XOR8)
    val providerUsercenterModifyAccountnameXor8: String
        get() = XORUtils.hash(PROVIDER_USERCENTER_MODIFY_ACCOUNTNAME_XOR8)
    val providerUsercenterAutologinServiceXor8: String
        get() = XORUtils.hash(PROVIDER_USERCENTER_AUTOLOGIN_SERVICE_XOR8)
}
