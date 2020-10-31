package com.curtisy.oppounlocker.heytap.utils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.util.*


object DBUtil {
    val mLockObject = Any()
    private fun closeDatabase(sQLiteDatabase: SQLiteDatabase?) {}
    private fun initDatabase(context: Context): SQLiteDatabase {
        var openDatabase: SQLiteDatabase
        synchronized(mLockObject) {
            DatabaseManager.initializeInstance(DBHelper.getInstance(context))
            openDatabase = DatabaseManager.getInstance().openDatabase()
        }
        return openDatabase
    }

    private fun closeCursor(cursor: Cursor?) {
        if (cursor != null) {
            try {
                if (!cursor.isClosed()) {
                    cursor.close()
                }
            } catch (unused: Exception) {
                LogUtil.m6340d("closeCursor--Exception")
            }
        }
    }

    private fun closeSource(sQLiteDatabase: SQLiteDatabase?, cursor: Cursor?) {
        closeCursor(cursor)
        closeDatabase(sQLiteDatabase)
    }

    /* JADX WARN: Multi-variable type inference failed */ /* JADX WARN: Type inference failed for: r12v2, types: [android.database.Cursor] */ /* JADX WARN: Type inference failed for: r12v11 */ /* JADX WARNING: Removed duplicated region for block: B:42:0x00a1  */ /* Code decompiled incorrectly, please refer to instructions dump. */
    fun getAddressInfoList(r11: Context?, r12: Int): LinkedList<neton.client.dns.AddressInfo> {
        /*
            java.lang.String r0 = "getAddressInfoList: start."
            neton.client.Utils.LogUtil.m6346i(r0)
            java.lang.Object r0 = neton.client.database.DBUtil.mLockObject
            monitor-enter(r0)
            r1 = 0
            android.database.sqlite.SQLiteDatabase r11 = initDatabase(r11)     // Catch:{ Exception -> 0x0076, all -> 0x0070 }
            java.lang.String r5 = "dnsType=?"
            r2 = 1
            java.lang.String[] r6 = new java.lang.String[r2]     // Catch:{ Exception -> 0x006d, all -> 0x0068 }
            r2 = 0
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ Exception -> 0x006d, all -> 0x0068 }
            r6[r2] = r12     // Catch:{ Exception -> 0x006d, all -> 0x0068 }
            java.lang.String r3 = "address_info"
            r4 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r11
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x006d, all -> 0x0068 }
            java.util.LinkedList r2 = new java.util.LinkedList     // Catch:{ Exception -> 0x0065, all -> 0x0060 }
            r2.<init>()     // Catch:{ Exception -> 0x0065, all -> 0x0060 }
            if (r12 == 0) goto L_0x0041
            boolean r3 = r12.moveToLast()     // Catch:{ Exception -> 0x003f }
            if (r3 == 0) goto L_0x0041
        L_0x0031:
            neton.client.dns.AddressInfo r3 = neton.client.dns.AddressInfo.getAddressInfoByCursor(r12)     // Catch:{ Exception -> 0x003f }
            r2.add(r3)     // Catch:{ Exception -> 0x003f }
            boolean r3 = r12.moveToPrevious()     // Catch:{ Exception -> 0x003f }
            if (r3 != 0) goto L_0x0031
            goto L_0x0041
        L_0x003f:
            r3 = move-exception
            goto L_0x007a
        L_0x0041:
            java.lang.String r1 = "getAddressInfoList: finish."
            neton.client.Utils.LogUtil.m6346i(r1)     // Catch:{ all -> 0x00ba }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = "getAddressInfoList size:"
            r1.<init>(r3)     // Catch:{ all -> 0x00ba }
            int r3 = r2.size()     // Catch:{ all -> 0x00ba }
            r1.append(r3)     // Catch:{ all -> 0x00ba }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00ba }
            neton.client.Utils.LogUtil.m6340d(r1)     // Catch:{ all -> 0x00ba }
            closeSource(r11, r12)     // Catch:{ all -> 0x00ba }
            r1 = r2
            goto L_0x0097
        L_0x0060:
            r2 = move-exception
            r10 = r2
            r2 = r1
            r1 = r10
            goto L_0x009a
        L_0x0065:
            r3 = move-exception
            r2 = r1
            goto L_0x007a
        L_0x0068:
            r12 = move-exception
            r2 = r1
            r1 = r12
            r12 = r2
            goto L_0x009a
        L_0x006d:
            r3 = move-exception
            r12 = r1
            goto L_0x0079
        L_0x0070:
            r11 = move-exception
            r12 = r1
            r2 = r12
            r1 = r11
            r11 = r2
            goto L_0x009a
        L_0x0076:
            r3 = move-exception
            r11 = r1
            r12 = r11
        L_0x0079:
            r2 = r12
        L_0x007a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0099 }
            java.lang.String r5 = "getAddressInfoList:"
            r4.<init>(r5)     // Catch:{ all -> 0x0099 }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x0099 }
            r4.append(r3)     // Catch:{ all -> 0x0099 }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x0099 }
            neton.client.Utils.LogUtil.m6343e(r3)     // Catch:{ all -> 0x0099 }
            java.lang.String r2 = "getAddressInfoList: finish."
            neton.client.Utils.LogUtil.m6346i(r2)
            closeSource(r11, r12)
        L_0x0097:
            monitor-exit(r0)
            return r1
        L_0x0099:
            r1 = move-exception
        L_0x009a:
            java.lang.String r3 = "getAddressInfoList: finish."
            neton.client.Utils.LogUtil.m6346i(r3)
            if (r2 == 0) goto L_0x00b6
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "getAddressInfoList size:"
            r3.<init>(r4)
            int r2 = r2.size()
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            neton.client.Utils.LogUtil.m6340d(r2)
        L_0x00b6:
            closeSource(r11, r12)
            throw r1
        L_0x00ba:
            r11 = move-exception
            monitor-exit(r0)
            throw r11
        */
        throw UnsupportedOperationException("Method not decompiled: neton.client.database.DBUtil.getAddressInfoList(android.content.Context, int):java.util.LinkedList")
    }

    /* JADX WARN: Multi-variable type inference failed */ /* JADX WARN: Type inference failed for: r12v1, types: [android.database.Cursor] */ /* JADX WARN: Type inference failed for: r12v5 */ /* JADX WARN: Type inference failed for: r12v6, types: [android.database.Cursor] */ /* JADX WARN: Type inference failed for: r12v9 */ /* JADX WARN: Type inference failed for: r12v12 */ /* JADX WARN: Type inference failed for: r12v18 */ /* JADX WARNING: Removed duplicated region for block: B:45:0x00a4  */ /* Code decompiled incorrectly, please refer to instructions dump. */
    fun getAddressInfoList(r11: Context?, r12: Int, r13: String?): LinkedList<neton.client.dns.AddressInfo> {
        /*
            java.lang.String r0 = "getAddressInfoList: start."
            neton.client.Utils.LogUtil.m6346i(r0)
            java.lang.Object r0 = neton.client.database.DBUtil.mLockObject
            monitor-enter(r0)
            r1 = 0
            android.database.sqlite.SQLiteDatabase r11 = initDatabase(r11)     // Catch:{ Exception -> 0x007b, all -> 0x0075 }
            java.lang.String r5 = "host=? and dnsType=?"
            r2 = 2
            java.lang.String[] r6 = new java.lang.String[r2]     // Catch:{ Exception -> 0x0072, all -> 0x006d }
            r2 = 0
            r6[r2] = r13     // Catch:{ Exception -> 0x0072, all -> 0x006d }
            r13 = 1
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ Exception -> 0x0072, all -> 0x006d }
            r6[r13] = r12     // Catch:{ Exception -> 0x0072, all -> 0x006d }
            java.lang.String r3 = "address_info"
            r4 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r11
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0072, all -> 0x006d }
            if (r12 == 0) goto L_0x004d
            boolean r13 = r12.moveToLast()     // Catch:{ Exception -> 0x004a, all -> 0x0045 }
            if (r13 == 0) goto L_0x004d
            java.util.LinkedList r13 = new java.util.LinkedList     // Catch:{ Exception -> 0x004a, all -> 0x0045 }
            r13.<init>()     // Catch:{ Exception -> 0x004a, all -> 0x0045 }
        L_0x0034:
            neton.client.dns.AddressInfo r2 = neton.client.dns.AddressInfo.getAddressInfoByCursor(r12)     // Catch:{ Exception -> 0x0043 }
            r13.add(r2)     // Catch:{ Exception -> 0x0043 }
            boolean r2 = r12.moveToPrevious()     // Catch:{ Exception -> 0x0043 }
            if (r2 != 0) goto L_0x0034
            r1 = r13
            goto L_0x004d
        L_0x0043:
            r2 = move-exception
            goto L_0x007f
        L_0x0045:
            r13 = move-exception
            r10 = r1
            r1 = r13
            r13 = r10
            goto L_0x009d
        L_0x004a:
            r2 = move-exception
            r13 = r1
            goto L_0x007f
        L_0x004d:
            java.lang.String r13 = "getAddressInfoList: finish."
            neton.client.Utils.LogUtil.m6346i(r13)     // Catch:{ all -> 0x00bd }
            if (r1 == 0) goto L_0x0069
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bd }
            java.lang.String r2 = "getAddressInfoList size:"
            r13.<init>(r2)     // Catch:{ all -> 0x00bd }
            int r2 = r1.size()     // Catch:{ all -> 0x00bd }
            r13.append(r2)     // Catch:{ all -> 0x00bd }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x00bd }
            neton.client.Utils.LogUtil.m6346i(r13)     // Catch:{ all -> 0x00bd }
        L_0x0069:
            closeSource(r11, r12)     // Catch:{ all -> 0x00bd }
            goto L_0x009a
        L_0x006d:
            r12 = move-exception
            r13 = r1
            r1 = r12
            r12 = r13
            goto L_0x009d
        L_0x0072:
            r2 = move-exception
            r12 = r1
            goto L_0x007e
        L_0x0075:
            r11 = move-exception
            r12 = r1
            r13 = r12
            r1 = r11
            r11 = r13
            goto L_0x009d
        L_0x007b:
            r2 = move-exception
            r11 = r1
            r12 = r11
        L_0x007e:
            r13 = r12
        L_0x007f:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x009c }
            java.lang.String r4 = "getAddressInfoList:"
            r3.<init>(r4)     // Catch:{ all -> 0x009c }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x009c }
            r3.append(r2)     // Catch:{ all -> 0x009c }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x009c }
            neton.client.Utils.LogUtil.m6343e(r2)     // Catch:{ all -> 0x009c }
            java.lang.String r13 = "getAddressInfoList: finish."
            neton.client.Utils.LogUtil.m6346i(r13)
            goto L_0x0069
        L_0x009a:
            monitor-exit(r0)
            return r1
        L_0x009c:
            r1 = move-exception
        L_0x009d:
            java.lang.String r2 = "getAddressInfoList: finish."
            neton.client.Utils.LogUtil.m6346i(r2)
            if (r13 == 0) goto L_0x00b9
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "getAddressInfoList size:"
            r2.<init>(r3)
            int r13 = r13.size()
            r2.append(r13)
            java.lang.String r13 = r2.toString()
            neton.client.Utils.LogUtil.m6346i(r13)
        L_0x00b9:
            closeSource(r11, r12)
            throw r1
        L_0x00bd:
            r11 = move-exception
            monitor-exit(r0)
            throw r11
        */
        throw UnsupportedOperationException("Method not decompiled: neton.client.database.DBUtil.getAddressInfoList(android.content.Context, int, java.lang.String):java.util.LinkedList")
    }

    fun getAddressInfo(context: Context, i: Int, str: String): AddressInfo? {
        var cursor: Cursor?
        var sQLiteDatabase: SQLiteDatabase?
        var addressInfo: AddressInfo?
        LogUtil.m6346i("getAddressInfo: start..--dnsType:$i,url:$str")
        synchronized(mLockObject) {
            try {
                sQLiteDatabase = initDatabase(context)
                try {
                    cursor = sQLiteDatabase!!.query(DBHelper.TABLE_IP_INFO, null, "host=? and dnsType=?", arrayOf(str, i.toString()), null, null, null)
                } catch (e: Exception) {
                    e = e
                    cursor = null
                    return try {
                        LogUtil.m6343e("getAddressInfo:" + e.message)
                        LogUtil.m6346i("getAppMessageByMessageId: finish.")
                        closeSource(sQLiteDatabase, cursor)
                        addressInfo = null
                        addressInfo
                    } catch (th: Throwable) {
                        th = th
                        LogUtil.m6346i("getAppMessageByMessageId: finish.")
                        closeSource(sQLiteDatabase, cursor)
                        throw th
                    }
                } catch (th2: Throwable) {
                    th = th2
                    cursor = null
                    LogUtil.m6346i("getAppMessageByMessageId: finish.")
                    closeSource(sQLiteDatabase, cursor)
                    throw th
                }
                try {
                    addressInfo = AddressInfo.getAddressInfoByCursor(cursor)
                    LogUtil.m6346i("getAppMessageByMessageId: finish.")
                    closeSource(sQLiteDatabase, cursor)
                } catch (e2: Exception) {
                    e = e2
                    LogUtil.m6343e("getAddressInfo:" + e.getMessage())
                    LogUtil.m6346i("getAppMessageByMessageId: finish.")
                    closeSource(sQLiteDatabase, cursor)
                    addressInfo = null
                    return addressInfo
                }
            } catch (e3: Exception) {
                e = e3
                sQLiteDatabase = null
                cursor = null
                LogUtil.m6343e("getAddressInfo:" + e.getMessage())
                LogUtil.m6346i("getAppMessageByMessageId: finish.")
                closeSource(sQLiteDatabase, cursor)
                addressInfo = null
                return addressInfo
            } catch (th3: Throwable) {
                th = th3
                sQLiteDatabase = null
                cursor = null
                LogUtil.m6346i("getAppMessageByMessageId: finish.")
                closeSource(sQLiteDatabase, cursor)
                throw th
            }
        }
        return addressInfo
    }

    fun getIpInfoList(context: Context, i: Int, str: String): LinkedList<IpInfo?>? {
        var linkedList: LinkedList<IpInfo?>?
        var sQLiteDatabase: SQLiteDatabase?
        var cursor: Cursor?
        synchronized(mLockObject) {
            linkedList = null
            try {
                sQLiteDatabase = initDatabase(context)
                try {
                    cursor = sQLiteDatabase!!.query(DBHelper.TABLE_IP_INFO, null, "host=? and dnsType=?", arrayOf(str, i.toString()), null, null, null)
                    if (cursor != null) {
                        try {
                            if (cursor.moveToLast()) {
                                val linkedList2: LinkedList<IpInfo?> = LinkedList()
                                do {
                                    linkedList2.add(IpInfo.getIpInfoByCursor(str, i, cursor))
                                } while (cursor.moveToPrevious())
                                linkedList = linkedList2
                            }
                        } catch (e: Exception) {
                            e = e
                            th = try {
                                LogUtil.m6343e("getIpInfoList:" + e.message)
                                closeSource(sQLiteDatabase, cursor)
                                return linkedList
                            } catch (th: Throwable) {
                                th
                            }
                        }
                    }
                } catch (e2: Exception) {
                    e = e2
                    cursor = null
                    LogUtil.m6343e("getIpInfoList:" + e.getMessage())
                    closeSource(sQLiteDatabase, cursor)
                    return linkedList
                } catch (th2: Throwable) {
                    th = th2
                    cursor = null
                    closeSource(sQLiteDatabase, cursor)
                    throw th
                }
            } catch (e3: Exception) {
                e = e3
                sQLiteDatabase = null
                cursor = null
                LogUtil.m6343e("getIpInfoList:" + e.getMessage())
                closeSource(sQLiteDatabase, cursor)
                return linkedList
            } catch (th3: Throwable) {
                th = th3
                sQLiteDatabase = null
                cursor = null
                closeSource(sQLiteDatabase, cursor)
                throw th
            }
            closeSource(sQLiteDatabase, cursor)
        }
        return linkedList
    }

    fun addAddressInfo(context: Context, addressInfo: AddressInfo) {
        LogUtil.m6346i("addAddressInfo start.:" + addressInfo.toString())
        synchronized(mLockObject) {
            try {
                val contentValues = ContentValues()
                contentValues.put(DnsInfo.HOST, addressInfo.getHost())
                contentValues.put(DnsInfo.DNS_TYPE, Integer.valueOf(addressInfo.getDnsType()))
                contentValues.put(DnsInfo.TTL, java.lang.Long.valueOf(addressInfo.getTtl()))
                contentValues.put(AddressInfo.TIMESTAMP, java.lang.Long.valueOf(addressInfo.getTimeStamp()))
                addDataToDB(context, DBHelper.TABLE_ADDRESS_INFO, contentValues)
                LogUtil.m6346i("addAddressInfo finish,")
            } catch (unused: Exception) {
                LogUtil.m6343e("addAddressInfo--Exception")
            }
        }
    }

    fun addIpInfo(context: Context, ipInfo: IpInfo) {
        LogUtil.m6346i("addIpInfo start.:" + java.lang.String.valueOf(ipInfo))
        synchronized(mLockObject) {
            try {
                val contentValues = ContentValues()
                contentValues.put(DnsInfo.HOST, ipInfo.getHost())
                contentValues.put(DnsInfo.DNS_TYPE, Integer.valueOf(ipInfo.getDnsType()))
                contentValues.put("ip", ipInfo.getIp())
                contentValues.put(IpInfo.PORT, Integer.valueOf(ipInfo.getPort()))
                contentValues.put(IpInfo.LOCAL, ipInfo.getLocal())
                contentValues.put(IpInfo.WEIGHT, Integer.valueOf(ipInfo.getWeight()))
                contentValues.put(IpInfo.f7045SP, ipInfo.getSp())
                contentValues.put(IpInfo.FAIL_COUNT, Integer.valueOf(ipInfo.getFailCount()))
                contentValues.put(IpInfo.FAIL_TIME, java.lang.Long.valueOf(ipInfo.getFailTime()))
                addDataToDB(context, DBHelper.TABLE_IP_INFO, contentValues)
                LogUtil.m6346i("addIpInfo finish,")
            } catch (unused: Exception) {
                LogUtil.m6343e("addIpInfo--Exception")
            }
        }
    }

    fun updateAddressInfo(context: Context, addressInfo: AddressInfo) {
        LogUtil.m6346i("updateAddressInfo start.:" + addressInfo.toString())
        val contentValues = ContentValues()
        contentValues.put(DnsInfo.HOST, addressInfo.getHost())
        contentValues.put(DnsInfo.DNS_TYPE, Integer.valueOf(addressInfo.getDnsType()))
        contentValues.put(DnsInfo.TTL, java.lang.Long.valueOf(addressInfo.getTtl()))
        contentValues.put(AddressInfo.TIMESTAMP, java.lang.Long.valueOf(addressInfo.getTimeStamp()))
        synchronized(mLockObject) {
            var sQLiteDatabase: SQLiteDatabase? = null
            try {
                var sQLiteDatabase2: SQLiteDatabase? = initDatabase(context)
                try {
                    val sb = StringBuilder()
                    sb.append(addressInfo.getDnsType())
                    sQLiteDatabase2!!.update(DBHelper.TABLE_ADDRESS_INFO, contentValues, "host=? and dnsType=?", arrayOf(addressInfo.getHost(), sb.toString()))
                    closeDatabase(sQLiteDatabase2)
                } catch (e: Exception) {
                    e = e
                    sQLiteDatabase = sQLiteDatabase2
                    try {
                        LogUtil.m6343e("updateAddressInfo--Exception:$e")
                        closeDatabase(sQLiteDatabase)
                    } catch (th: Throwable) {
                        th = th
                        sQLiteDatabase2 = sQLiteDatabase
                        closeDatabase(sQLiteDatabase2)
                        throw th
                    }
                } catch (th2: Throwable) {
                    th = th2
                    closeDatabase(sQLiteDatabase2)
                    throw th
                }
            } catch (e2: Exception) {
                e = e2
                LogUtil.m6343e("updateAddressInfo--Exception:" + e.toString())
                closeDatabase(sQLiteDatabase)
            }
        }
    }

    fun updateIpInfo(context: Context, ipInfo: IpInfo) {
        LogUtil.m6346i("updateIpInfo start.:" + ipInfo.toString())
        val contentValues = ContentValues()
        contentValues.put(IpInfo.FAIL_COUNT, Integer.valueOf(ipInfo.getFailCount()))
        contentValues.put(IpInfo.FAIL_TIME, java.lang.Long.valueOf(ipInfo.getFailTime()))
        synchronized(mLockObject) {
            var sQLiteDatabase: SQLiteDatabase? = null
            try {
                var sQLiteDatabase2: SQLiteDatabase? = initDatabase(context)
                try {
                    val sb = StringBuilder()
                    sb.append(ipInfo.getDnsType())
                    val sb2 = StringBuilder()
                    sb2.append(ipInfo.getPort())
                    sQLiteDatabase2!!.update(DBHelper.TABLE_IP_INFO, contentValues, "host=? and dnsType=? and ip=? and port=?", arrayOf(ipInfo.getHost(), sb.toString(), ipInfo.getIp(), sb2.toString()))
                    closeDatabase(sQLiteDatabase2)
                } catch (e: Exception) {
                    e = e
                    sQLiteDatabase = sQLiteDatabase2
                    try {
                        LogUtil.m6343e("updateIpInfo--Exception:$e")
                        closeDatabase(sQLiteDatabase)
                    } catch (th: Throwable) {
                        th = th
                        sQLiteDatabase2 = sQLiteDatabase
                        closeDatabase(sQLiteDatabase2)
                        throw th
                    }
                } catch (th2: Throwable) {
                    th = th2
                    closeDatabase(sQLiteDatabase2)
                    throw th
                }
            } catch (e2: Exception) {
                e = e2
                LogUtil.m6343e("updateIpInfo--Exception:" + e.toString())
                closeDatabase(sQLiteDatabase)
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x006e A[SYNTHETIC, Splitter:B:27:0x006e] */ /* JADX WARNING: Removed duplicated region for block: B:35:0x008f A[SYNTHETIC, Splitter:B:35:0x008f] */ /* Code decompiled incorrectly, please refer to instructions dump. */
    private fun addDataToDB(r3: Context, r4: String, r5: ContentValues) {
        /*
            java.lang.Object r0 = neton.client.database.DBUtil.mLockObject
            monitor-enter(r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ad }
            java.lang.String r2 = "addDataToDB-table:"
            r1.<init>(r2)     // Catch:{ all -> 0x00ad }
            r1.append(r4)     // Catch:{ all -> 0x00ad }
            java.lang.String r2 = ",values:"
            r1.append(r2)     // Catch:{ all -> 0x00ad }
            r1.append(r5)     // Catch:{ all -> 0x00ad }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00ad }
            neton.client.Utils.LogUtil.m6346i(r1)     // Catch:{ all -> 0x00ad }
            if (r5 != 0) goto L_0x0020
            monitor-exit(r0)     // Catch:{ all -> 0x00ad }
            return
        L_0x0020:
            r1 = 0
            android.database.sqlite.SQLiteDatabase r3 = initDatabase(r3)     // Catch:{ Exception -> 0x0056 }
            r3.beginTransaction()     // Catch:{ Exception -> 0x0051, all -> 0x004e }
            r3.insert(r4, r1, r5)     // Catch:{ Exception -> 0x0051, all -> 0x004e }
            r3.setTransactionSuccessful()     // Catch:{ Exception -> 0x0051, all -> 0x004e }
            if (r3 == 0) goto L_0x004a
            r3.endTransaction()     // Catch:{ Exception -> 0x0034 }
            goto L_0x004a
        L_0x0034:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r1 = "addDataToDB--endTransaction--Exception:"
            r5.<init>(r1)
            java.lang.String r4 = r4.toString()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            neton.client.Utils.LogUtil.m6343e(r4)
        L_0x004a:
            closeDatabase(r3)
            goto L_0x008b
        L_0x004e:
            r4 = move-exception
            r1 = r3
            goto L_0x008d
        L_0x0051:
            r4 = move-exception
            r1 = r3
            goto L_0x0057
        L_0x0054:
            r4 = move-exception
            goto L_0x008d
        L_0x0056:
            r4 = move-exception
        L_0x0057:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0054 }
            java.lang.String r5 = "addDataToDB--Exception:"
            r3.<init>(r5)     // Catch:{ all -> 0x0054 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0054 }
            r3.append(r4)     // Catch:{ all -> 0x0054 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0054 }
            neton.client.Utils.LogUtil.m6343e(r3)     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x0088
            r1.endTransaction()     // Catch:{ Exception -> 0x0072 }
            goto L_0x0088
        L_0x0072:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "addDataToDB--endTransaction--Exception:"
            r4.<init>(r5)
            java.lang.String r3 = r3.toString()
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            neton.client.Utils.LogUtil.m6343e(r3)
        L_0x0088:
            closeDatabase(r1)
        L_0x008b:
            monitor-exit(r0)
            return
        L_0x008d:
            if (r1 == 0) goto L_0x00a9
            r1.endTransaction()     // Catch:{ Exception -> 0x0093 }
            goto L_0x00a9
        L_0x0093:
            r3 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r2 = "addDataToDB--endTransaction--Exception:"
            r5.<init>(r2)
            java.lang.String r3 = r3.toString()
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            neton.client.Utils.LogUtil.m6343e(r3)
        L_0x00a9:
            closeDatabase(r1)
            throw r4
        L_0x00ad:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw UnsupportedOperationException("Method not decompiled: neton.client.database.DBUtil.addDataToDB(android.content.Context, java.lang.String, android.content.ContentValues):void")
    }

    private fun deleteAllInTable(context: Context, str: String) {
        synchronized(mLockObject) {
            LogUtil.m6346i("deleteAllInTable-table:$str")
            var sQLiteDatabase: SQLiteDatabase? = null
            try {
                val initDatabase = initDatabase(context)
                try {
                    initDatabase.delete(str, null, null)
                    closeDatabase(initDatabase)
                } catch (unused: Exception) {
                    sQLiteDatabase = initDatabase
                    try {
                        LogUtil.m6343e("deleteAllInTable--Exception")
                        closeDatabase(sQLiteDatabase)
                    } catch (th: Throwable) {
                        th = th
                        closeDatabase(sQLiteDatabase)
                        throw th
                    }
                } catch (th2: Throwable) {
                    th = th2
                    sQLiteDatabase = initDatabase
                    closeDatabase(sQLiteDatabase)
                    throw th
                }
            } catch (unused2: Exception) {
                LogUtil.m6343e("deleteAllInTable--Exception")
                closeDatabase(sQLiteDatabase)
            }
        }
    }

    fun deleteAddressInTable(context: Context, str: String, str2: String, i: Int) {
        synchronized(mLockObject) {
            LogUtil.m6346i("deleteAddressInTable-table:$str,url:$str2,dnsType:$i")
            var sQLiteDatabase: SQLiteDatabase? = null
            try {
                var sQLiteDatabase2: SQLiteDatabase? = initDatabase(context)
                try {
                    sQLiteDatabase2!!.delete(str, "host=? and dnsType=?", arrayOf(str2, i.toString()))
                    closeDatabase(sQLiteDatabase2)
                } catch (unused: Exception) {
                    sQLiteDatabase = sQLiteDatabase2
                    try {
                        LogUtil.m6343e("deleteAddressInTable--Exception")
                        closeDatabase(sQLiteDatabase)
                    } catch (th: Throwable) {
                        th = th
                        sQLiteDatabase2 = sQLiteDatabase
                        closeDatabase(sQLiteDatabase2)
                        throw th
                    }
                } catch (th2: Throwable) {
                    th = th2
                    closeDatabase(sQLiteDatabase2)
                    throw th
                }
            } catch (unused2: Exception) {
                LogUtil.m6343e("deleteAddressInTable--Exception")
                closeDatabase(sQLiteDatabase)
            }
        }
    }

    fun deleteIpInfosByHostAndDnsType(context: Context, str: String, i: Int) {
        synchronized(mLockObject) {
            LogUtil.m6346i("deleteIpInfosByHostAndDnsType-host:$str,dnsType:$i")
            var sQLiteDatabase: SQLiteDatabase? = null
            try {
                var sQLiteDatabase2: SQLiteDatabase? = initDatabase(context)
                try {
                    val delete = sQLiteDatabase2!!.delete(DBHelper.TABLE_IP_INFO, "host=? and dnsType=?", arrayOf(str, i.toString()))
                    LogUtil.m6346i("deleteIpInfosByHostAndDnsType-host:$str,dnsType:$i,count:$delete")
                    closeDatabase(sQLiteDatabase2)
                } catch (e: Exception) {
                    e = e
                    sQLiteDatabase = sQLiteDatabase2
                    try {
                        LogUtil.m6343e("deleteIpInfosByHostAndDnsType--Exception:" + e.message)
                        closeDatabase(sQLiteDatabase)
                    } catch (th: Throwable) {
                        th = th
                        sQLiteDatabase2 = sQLiteDatabase
                        closeDatabase(sQLiteDatabase2)
                        throw th
                    }
                } catch (th2: Throwable) {
                    th = th2
                    closeDatabase(sQLiteDatabase2)
                    throw th
                }
            } catch (e2: Exception) {
                e = e2
                LogUtil.m6343e("deleteIpInfosByHostAndDnsType--Exception:" + e.getMessage())
                closeDatabase(sQLiteDatabase)
            }
        }
    }
}
