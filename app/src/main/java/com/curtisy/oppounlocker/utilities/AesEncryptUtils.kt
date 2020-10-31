/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.curtisy.oppounlocker.utilities

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.curtisy.oppounlocker.data.Param
import com.curtisy.oppounlocker.data.Response
import com.google.gson.Gson
import org.apache.commons.codec.binary.Base64
import java.security.KeyFactory
import java.security.MessageDigest
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


class AesEncryptUtils {
    companion object {
        private var f6727a: String? = null
        private val f6743c = byteArrayOf(13, 10)

        fun m6015a() {
            if (TextUtils.isEmpty(f6727a)) {
                try {
                    val instance = KeyGenerator.getInstance("AES")
                    instance.init(256)
                    f6727a = String(Base64.encodeBase64(instance.generateKey().encoded))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun m6011a(context: Context): String? {
            m6015a();
            val b = m6016b(context)
            return if (TextUtils.isEmpty(b)) {
                null
            } else m6013a(f6727a, b)
        }

        private fun m6016b(r3: Context): String {
            val certLocation = "lk_unlock.crt"
            val inputStream = r3.assets.open(certLocation)
            val certType = "X.509"
            val certificateFactory = CertificateFactory.getInstance(certType)
            val certificate = certificateFactory.generateCertificate(inputStream) as X509Certificate
            val publicKey = certificate.publicKey
            val encodedKey = publicKey.encoded
            val result = Base64.encodeBase64(encodedKey)
            inputStream.close()
            val stringifiedResult = String(result)

            Log.d("DECRYPTOR", "Public key is: $stringifiedResult")

            return stringifiedResult
        }

        private fun m6013a(str: String?, str2: String): String? {
            return try {
                val x509EncodedKeySpec = X509EncodedKeySpec(Base64.decodeBase64(str2.toByteArray(charset("UTF-8"))))
                val instance: Cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
                instance.init(1, KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec) as RSAPublicKey)
                String(Base64.encodeBase64(instance.doFinal(str!!.toByteArray(charset("UTF-8")))))
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        fun m6012a(str: String): String? {
            val c = m6019c(str, f6727a)
            if (c != null) {
                return Gson().toJson(Param(c))
            }
            return null
        }

        fun m6017b(str: String?): String? {
            val eVar = try {
                Gson().fromJson(str, Response::class.java)
            } catch (unused: Exception) {
                null
            }
            return if (eVar?.resps == null) {
                null
            } else m6018b(eVar.resps, f6727a)
        }

        private fun m6018b(str: String, str2: String?): String? {
            return try {
                val secretKeySpec = SecretKeySpec(Base64.decodeBase64(str2!!.toByteArray(charset("UTF-8"))), "AES")
                val ivParameterSpec = IvParameterSpec(m6020c(m6014a(Base64.decodeBase64(str2.toByteArray(charset("UTF-8"))))))
                val instance: Cipher = Cipher.getInstance("AES/CTR/NoPadding")
                instance.init(2, secretKeySpec, ivParameterSpec)
                String(instance.doFinal(Base64.decodeBase64(str.toByteArray(charset("UTF-8")))), charset("UTF-8"))
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        private fun m6019c(str: String, str2: String?): String? {
            return try {
                val secretKeySpec = SecretKeySpec(Base64.decodeBase64(str2!!.toByteArray(charset("UTF-8"))), "AES")
                val ivParameterSpec = IvParameterSpec(m6020c(m6014a(Base64.decodeBase64(str2.toByteArray(charset("UTF-8"))))))
                val instance: Cipher = Cipher.getInstance("AES/CTR/NoPadding")
                instance.init(2, secretKeySpec, ivParameterSpec)
                String(Base64.encodeBase64(instance.doFinal(str.toByteArray(charset("UTF-8")))))
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        private fun m6020c(str: String?): ByteArray? {
            val length = str!!.length / 2
            val bArr = ByteArray(length)
            for (i in 0 until length) {
                val i2 = i * 2
                bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).toByte()
            }
            return bArr
        }

        private fun m6014a(bArr: ByteArray?): String? {
            return if (bArr == null) {
                null
            } else try {
                val instance: MessageDigest = MessageDigest.getInstance("MD5")
                instance.update(bArr)
                val digest = instance.digest()
                val sb = StringBuilder()
                for (b in digest) {
                    val i = b.toInt() and 255
                    if (i shr 4 == 0) {
                        sb.append("0")
                        sb.append(Integer.toHexString(i))
                    } else {
                        sb.append(Integer.toHexString(i))
                    }
                }
                sb.toString()
            } catch (unused: Exception) {
                null
            }
        }
    }
}