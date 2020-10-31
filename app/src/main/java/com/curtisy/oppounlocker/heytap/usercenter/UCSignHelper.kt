package com.curtisy.oppounlocker.heytap.usercenter

import android.text.TextUtils
import com.curtisy.oppounlocker.BuildConfig
import com.curtisy.oppounlocker.heytap.annotations.NoSign
import java.lang.reflect.Field
import java.util.*
import kotlin.collections.ArrayList


object UCSignHelper {
    /* renamed from: b */
    private fun m6315b(obj: Any): ArrayList<String> {
        var obj2: Any
        val arrayList: ArrayList<String> = ArrayList()
        try {
            val arrayList2: ArrayList<Field> = ArrayList()
            var cls = obj.javaClass
            while (cls != Any::class.java) {
                arrayList2.addAll(cls.declaredFields)
                cls = cls.superclass
            }
            for (field in arrayList2) {
                field.isAccessible = true
                if (field.getAnnotation(NoSign::class.java) == null && field.get(obj).also { obj2 = it } != null && obj2 !== BuildConfig.FLAVOR && !TextUtils.isEmpty(obj2.toString())) {
                    val name: String = field.name
                    arrayList.add("$name=$obj2&")
                }
            }
        } catch (e: IllegalArgumentException) {
        } catch (e2: IllegalAccessException) {
        }
        return arrayList
    }

    /* renamed from: a */
    fun m6314a(obj: Any): String {
        val b = m6315b(obj)
        if (b.isEmpty()) {
            return null
        }
        val size = b.size
        val strArr = b.toArray(arrayOfNulls<String>(size)) as Array<String>
        Arrays.sort(strArr, java.lang.String.CASE_INSENSITIVE_ORDER)
        val sb = StringBuilder()
        for (i in 0 until size) {
            sb.append(strArr[i])
        }
        return sb.toString()
    }
}