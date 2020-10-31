package com.curtisy.oppounlocker.heytap.account

class AccountNameTask {
    interface onReqAccountCallback<T> {
        fun onReqFinish(t: T)
        fun onReqLoading()
        fun onReqStart()
    }
}