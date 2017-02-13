package jsapi.electron

object session {

    private val module: dynamic = js("require('electron').session")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * If partition starts with persist:, the page will use a persistent session 
     * available to all pages in the app with the same partition. if there is no 
     * persist: prefix, the page will use an in-memory session. If the partition is 
     * empty then default session of the app will be returned.
     *
     * To create a Session with options, you have to ensure the Session with the 
     * partition has never been used before. There is no way to change the options of 
     * an existing Session object.
     */
    fun fromPartition(partition: String, options: FromPartitionOptions): Session = 
        module.fromPartition(partition, options)

    // ~ Builders ------------------------------------------------------------------------------

    class FromPartitionOptions(
        var cache: Boolean
    )
}

class Session() {

    val instance: dynamic

    init {
        val _constructor = js("require('electron').Session")
        instance = js("new _constructor(_)")
    }

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Properties ----------------------------------------------------------------------------

    val cookies: dynamic get() = instance.cookies
    val webRequest: dynamic get() = instance.webRequest
    val protocol: dynamic get() = instance.protocol

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Callback is invoked with the session's current cache size.
     */
    fun getCacheSize(callback: (size: Int) -> Unit): Unit = 
        instance.getCacheSize(callback)

    /**
     * Clears the session’s HTTP cache.
     */
    fun clearCache(callback: () -> Unit): Unit = 
        instance.clearCache(callback)

    /**
     * Clears the data of web storages.
     */
    fun clearStorageData(options: ClearStorageDataOptions?, callback: (() -> Unit)?): Unit = 
        instance.clearStorageData(options, callback)

    /**
     * Writes any unwritten DOMStorage data to disk.
     */
    fun flushStorageData(): Unit = 
        instance.flushStorageData()

    /**
     * Sets the proxy settings.
     *
     * When pacScript and proxyRules are provided together, the proxyRules option is 
     * ignored and pacScript configuration is applied.
     *
     * The proxyRules has to follow the rules below:
     *
     *  | 
     *  | proxyRules = schemeProxies[";"<schemeProxies>]
     *  | schemeProxies = [<urlScheme>"="]<proxyURIList>
     *  | urlScheme = "http" | "https" | "ftp" | "socks"
     *  | proxyURIList = <proxyURL>[","<proxyURIList>]
     *  | proxyURL = [<proxyScheme>"://"]<proxyHost>[":"<proxyPort>]
     *  | 
     *
     * For example:
     *
     *  . http=foopy:80;ftp=foopy2 - Use HTTP proxy foopy:80 for http:// URLs, and HTTP 
     *    proxy foopy2:80 for ftp:// URLs.
     *
     *  . foopy:80 - Use HTTP proxy foopy:80 for all URLs.
     *
     *  . foopy:80,bar,direct:// - Use HTTP proxy foopy:80 for all URLs, failing over to 
     *    bar if foopy:80 is unavailable, and after that using no proxy.
     *
     *  . socks4://foopy - Use SOCKS v4 proxy foopy:1080 for all URLs.
     *
     *  . http=foopy,socks5://bar.com - Use HTTP proxy foopy for http URLs, and fail 
     *    over to the SOCKS5 proxy bar.com if foopy is unavailable.
     *
     *  . http=foopy,direct:// - Use HTTP proxy foopy for http URLs, and use no proxy if 
     *    foopy is unavailable.
     *
     *  . http=foopy;socks=foopy2 - Use HTTP proxy foopy for http URLs, and use 
     *    socks4://foopy2 for all other URLs.
     *
     * The proxyBypassRules is a comma separated list of rules described below:
     *
     *  . [ URL_SCHEME "://" ] HOSTNAME_PATTERN [ ":" <port> ]
     *
     * Match all hostnames that match the pattern HOSTNAME_PATTERN.
     *
     * Examples: "foobar.com", "foobar.com", ".foobar.com", "foobar.com:99", 
     * "https://x..y.com:99"
     *
     *  . "." HOSTNAME_SUFFIX_PATTERN [ ":" PORT ]
     *
     * Match a particular domain suffix.
     *
     * Examples: ".google.com", ".com", "http://.google.com"
     *
     *  . [ SCHEME "://" ] IP_LITERAL [ ":" PORT ]
     *
     * Match URLs which are IP address literals.
     *
     * Examples: "127.0.1", "[0:0::1]", "[::1]", "http://[::1]:99"
     *
     *  . IP_LITERAL "/" PREFIX_LENGHT_IN_BITS
     *
     * Match any URL that is to an IP literal that falls between the given range. IP 
     * range is specified using CIDR notation.
     *
     * Examples: "192.168.1.1/16", "fefe:13::abc/33".
     *
     *  . <local>
     *
     * Match local addresses. The meaning of <local> is whether the host matches one 
     * of: "127.0.0.1", "::1", "localhost".
     */
    fun setProxy(config: SetProxyConfig, callback: () -> Unit): Unit = 
        instance.setProxy(config, callback)

    /**
     * Resolves the proxy information for url. The callback will be called with 
     * callback(proxy) when the request is performed.
     */
    fun resolveProxy(url: dynamic, callback: (proxy: ResolveProxyProxy.() -> Unit) -> Unit): Unit = 
        instance.resolveProxy(url.instance, callback)

    /**
     * Sets download saving directory. By default, the download directory will be the 
     * Downloads under the respective app folder.
     */
    fun setDownloadPath(path: String): Unit = 
        instance.setDownloadPath(path)

    /**
     * Emulates network with the given configuration for the session.
     *
     *  | 
     *  | // To emulate a GPRS connection with 50kbps throughput and 500 ms latency.
     *  | window.webContents.session.enableNetworkEmulation({
     *  |   latency: 500,
     *  |   downloadThroughput: 6400,
     *  |   uploadThroughput: 6400
     *  | })
     *  | 
     *  | // To emulate a network outage.
     *  | window.webContents.session.enableNetworkEmulation({offline: true})
     *  | 
     */
    fun enableNetworkEmulation(options: EnableNetworkEmulationOptions.() -> Unit): Unit = 
        instance.enableNetworkEmulation(options.let { EnableNetworkEmulationOptions().apply(it) })

    /**
     * Disables any network emulation already active for the session. Resets to the 
     * original network configuration.
     */
    fun disableNetworkEmulation(): Unit = 
        instance.disableNetworkEmulation()

    /**
     * Sets the certificate verify proc for session, the proc will be called with 
     * proc(hostname, certificate, callback) whenever a server certificate 
     * verification is requested. Calling callback(true) accepts the certificate, 
     * calling callback(false) rejects it.
     *
     * Calling setCertificateVerifyProc(null) will revert back to default certificate 
     * verify proc.
     *
     *  | 
     *  | const {BrowserWindow} = require('electron')
     *  | let win = new BrowserWindow()
     *  | 
     *  | win.webContents.session.setCertificateVerifyProc((hostname, cert, callback) => {
     *  |   callback(hostname === 'github.com')
     *  | })
     *  | 
     */
    fun setCertificateVerifyProc(proc: (hostname: String, certificate: Certificate, callback: (isTrusted: Boolean) -> Unit) -> Unit): Unit = 
        instance.setCertificateVerifyProc(proc)

    /**
     * Sets the handler which can be used to respond to permission requests for the 
     * session. Calling callback(true) will allow the permission and callback(false) 
     * will reject it.
     *
     *  | 
     *  | const {session} = require('electron')
     *  | session.fromPartition('some-partition').setPermissionRequestHandler((webContents, permission, callback) => {
     *  |   if (webContents.getURL() === 'some-host' && permission === 'notifications') {
     *  |     return callback(false) // denied.
     *  |   }
     *  | 
     *  |   callback(true)
     *  | })
     *  | 
     */
    fun setPermissionRequestHandler(handler: (webContents: SetPermissionRequestHandlerWebContents.() -> Unit, permission: String, callback: (permissionGranted: Boolean) -> Unit) -> Unit): Unit = 
        instance.setPermissionRequestHandler(handler)

    /**
     * Clears the host resolver cache.
     */
    fun clearHostResolverCache(callback: (() -> Unit)?): Unit = 
        instance.clearHostResolverCache(callback)

    /**
     * Dynamically sets whether to always send credentials for HTTP NTLM or Negotiate 
     * authentication.
     *
     *  | 
     *  | const {session} = require('electron')
     *  | // consider any url ending with `example.com`, `foobar.com`, `baz`
     *  | // for integrated authentication.
     *  | session.defaultSession.allowNTLMCredentialsForDomains('*example.com, *foobar.com, *baz')
     *  | 
     *  | // consider all urls for integrated authentication.
     *  | session.defaultSession.allowNTLMCredentialsForDomains('*')
     *  | 
     */
    fun allowNTLMCredentialsForDomains(domains: String): Unit = 
        instance.allowNTLMCredentialsForDomains(domains)

    /**
     * Overrides the userAgent and acceptLanguages for this session.
     *
     * The acceptLanguages must a comma separated ordered list of language codes, for 
     * example "en-US,fr,de,ko,zh-CN,ja".
     *
     * This doesn't affect existing WebContents, and each WebContents can use 
     * webContents.setUserAgent to override the session-wide user agent.
     */
    fun setUserAgent(userAgent: String, acceptLanguages: String?): Unit = 
        instance.setUserAgent(userAgent, acceptLanguages)

    /**
     */
    fun getUserAgent(): String = 
        instance.getUserAgent()

    /**
     */
    fun getBlobData(identifier: String, callback: (result: dynamic) -> Unit): dynamic = 
        instance.getBlobData(identifier, callback)

    /**
     * Allows resuming cancelled or interrupted downloads from previous Session. The 
     * API will generate a DownloadItem that can be accessed with the will-download 
     * event. The DownloadItem will not have any WebContents associated with it and 
     * the initial state will be interrupted. The download will start only when the 
     * resume API is called on the DownloadItem.
     */
    fun createInterruptedDownload(options: CreateInterruptedDownloadOptions): Unit = 
        instance.createInterruptedDownload(options)

    /**
     * Clears the session’s HTTP authentication cache.
     */
    fun clearAuthCache(options: dynamic, callback: (() -> Unit)?): Unit = 
        instance.clearAuthCache(options.instance, callback)

    // ~ Companion -----------------------------------------------------------------------------

    companion object { 

        private val module: dynamic = js("require('electron').Session")

    }

    // ~ Builders ------------------------------------------------------------------------------

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

