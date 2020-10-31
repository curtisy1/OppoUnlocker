package com.curtisy.oppounlocker.heytap.dns

open class DnsInfo {
    var dnsType = 0
    var host: String? = null
    var ttl: Long = 0

    constructor(str: String?, i: Int) {
        host = str
        dnsType = i
    }

    constructor() {}

    companion object {
        const val DNS_TYPE = "dnsType"
        const val HOST = "host"
        const val TTL = "ttl"
        var TYPE_HTTP = 1
        var TYPE_LOCAL = 0
        // neton.client.dns.DnsImp
        var dnsType = 0
    }
}