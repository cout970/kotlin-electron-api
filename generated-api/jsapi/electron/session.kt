package jsapi.electron

object session {

    private val module: dynamic = js("require('electron').session")

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun fromPartition(partition: String, options: FromPartitionOptions): Session = 
        module.fromPartition(partition, options)

    // ~ Builders -------------------------------------------------------------------------------

    class FromPartitionOptions(
        var cache: Boolean
    )

}

class Session() {

    private val module: dynamic = js("require('electron').Session")

    val instance: dynamic

    init {
        val _constructor = js("require('electron').Session")
        instance = js("new _constructor(_)")
    }

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun getCacheSize(callback: (size: Int) -> Unit): dynamic = 
        instance.getCacheSize(callback)

    fun clearCache(callback: () -> Unit): Unit = 
        instance.clearCache(callback)

    fun clearStorageData(options: ClearStorageDataOptions?, callback: (() -> Unit)?): Unit = 
        instance.clearStorageData(options, callback)

    fun flushStorageData(): Unit = 
        instance.flushStorageData()

    fun setProxy(config: SetProxyConfig, callback: () -> Unit): Unit = 
        instance.setProxy(config, callback)

    fun resolveProxy(url: dynamic, callback: (proxy: ResolveProxyProxy.() -> Unit) -> Unit): Unit = 
        instance.resolveProxy(url.instance, callback)

    fun setDownloadPath(path: String): Unit = 
        instance.setDownloadPath(path)

    fun enableNetworkEmulation(options: EnableNetworkEmulationOptions.() -> Unit): Unit = 
        instance.enableNetworkEmulation(options.let { EnableNetworkEmulationOptions().apply(it) })

    fun disableNetworkEmulation(): Unit = 
        instance.disableNetworkEmulation()

    fun setCertificateVerifyProc(proc: (hostname: String, certificate: Certificate, callback: (isTrusted: Boolean) -> Unit) -> Unit): Unit = 
        instance.setCertificateVerifyProc(proc)

    fun setPermissionRequestHandler(handler: (webContents: SetPermissionRequestHandlerWebContents.() -> Unit, permission: String, callback: (permissionGranted: Boolean) -> Unit) -> Unit): Unit = 
        instance.setPermissionRequestHandler(handler)

    fun clearHostResolverCache(callback: (() -> Unit)?): Unit = 
        instance.clearHostResolverCache(callback)

    fun allowNTLMCredentialsForDomains(domains: String): Unit = 
        instance.allowNTLMCredentialsForDomains(domains)

    fun setUserAgent(userAgent: String, acceptLanguages: String?): Unit = 
        instance.setUserAgent(userAgent, acceptLanguages)

    fun getUserAgent(): String = 
        instance.getUserAgent()

    fun getBlobData(identifier: String, callback: (result: dynamic) -> Unit): dynamic = 
        instance.getBlobData(identifier, callback)

    fun createInterruptedDownload(options: CreateInterruptedDownloadOptions): Unit = 
        instance.createInterruptedDownload(options)

    fun clearAuthCache(options: dynamic, callback: (() -> Unit)?): Unit = 
        instance.clearAuthCache(options.instance, callback)

    // ~ Builders -------------------------------------------------------------------------------

    class ClearStorageDataOptions(
        var origin: String, 
        var storages: Array<String>, 
        var quotas: Array<String>
    )

    class SetProxyConfig(
        var pacScript: String, 
        var proxyRules: String, 
        var proxyBypassRules: String
    )

    class ResolveProxyProxy(
    )

    class EnableNetworkEmulationOptions(
        var offline: Boolean? = null, 
        var latency: Double? = null, 
        var downloadThroughput: Double? = null, 
        var uploadThroughput: Double? = null
    )

    class SetPermissionRequestHandlerWebContents(
    )

    class CreateInterruptedDownloadOptions(
        var path: String, 
        var urlChain: Array<String>, 
        var mimeType: String? = null, 
        var offset: Int, 
        var length: Int, 
        var lastModified: String, 
        var eTag: String, 
        var startTime: Double? = null
    )

}

