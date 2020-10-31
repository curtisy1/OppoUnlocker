package com.curtisy.oppounlocker.heytap.usercenter

interface IUCRequestCallBack<T> {
    fun onReqFinish(t: T)
    fun onReqLoading(bArr: ByteArray?): Any?
    fun onReqStart()
}