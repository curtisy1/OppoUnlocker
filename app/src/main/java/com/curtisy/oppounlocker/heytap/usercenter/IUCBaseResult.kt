package com.curtisy.oppounlocker.heytap.usercenter

interface IUCBaseResult {
    fun parseNetworkResponse(bArr: ByteArray?): Any?
}