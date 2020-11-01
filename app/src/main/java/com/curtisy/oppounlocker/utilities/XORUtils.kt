package com.curtisy.oppounlocker.utilities

import kotlin.experimental.xor

class XORUtils {
    companion object {
        /* renamed from: a */
        fun hash(str: String): String {
            val bytes = str.toByteArray()
            val length = bytes.size
            for (i in 0 until length) {
                bytes[i] = (bytes[i] xor 8)
            }
            return String(bytes)
        }
    }
}