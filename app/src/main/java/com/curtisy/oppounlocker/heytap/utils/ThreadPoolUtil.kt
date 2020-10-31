package com.curtisy.oppounlocker.heytap.utils

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object ThreadPoolUtil {
    private var mFixedThreadExecutor: ExecutorService? = null
    private var mSingleExecutor: ExecutorService? = null
    @Synchronized
    fun execute(runnable: Runnable?) {
        synchronized(ThreadPoolUtil::class.java) {
            if (mFixedThreadExecutor == null) {
                mFixedThreadExecutor = Executors.newFixedThreadPool(10)
            }
            mFixedThreadExecutor?.execute(runnable)
        }
    }

    @Synchronized
    fun executeSingle(runnable: Runnable?) {
        synchronized(ThreadPoolUtil::class.java) {
            if (mSingleExecutor == null) {
                mSingleExecutor = Executors.newSingleThreadExecutor()
            }
            mSingleExecutor?.execute(runnable)
        }
    }

    fun close() {
        if (mFixedThreadExecutor != null) {
            mFixedThreadExecutor?.shutdown()
            mFixedThreadExecutor = null
        }
        if (mSingleExecutor != null) {
            mSingleExecutor?.shutdown()
            mSingleExecutor = null
        }
    }
}