package jsapi.electron

class WebRequest() {

    private val module: dynamic = js("require('electron').WebRequest")

    val instance: dynamic

    init {
        val _constructor = js("require('electron').WebRequest")
        instance = js("new _constructor(_)")
    }

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun onBeforeRequest(filter: OnBeforeRequestFilter.() -> Unit, listener: (details: OnBeforeRequestDetails, callback: (response: OnBeforeRequestResponse.() -> Unit) -> Unit) -> Unit): Unit = 
        instance.onBeforeRequest(filter.let { OnBeforeRequestFilter().apply(it) }, listener)

    fun onBeforeSendHeaders(filter: OnBeforeSendHeadersFilter.() -> Unit, listener: () -> Unit): Unit = 
        instance.onBeforeSendHeaders(filter.let { OnBeforeSendHeadersFilter().apply(it) }, listener)

    fun onSendHeaders(filter: OnSendHeadersFilter.() -> Unit, listener: (details: OnSendHeadersDetails) -> Unit): Unit = 
        instance.onSendHeaders(filter.let { OnSendHeadersFilter().apply(it) }, listener)

    fun onHeadersReceived(filter: OnHeadersReceivedFilter.() -> Unit, listener: () -> Unit): Unit = 
        instance.onHeadersReceived(filter.let { OnHeadersReceivedFilter().apply(it) }, listener)

    fun onResponseStarted(filter: OnResponseStartedFilter.() -> Unit, listener: (details: OnResponseStartedDetails) -> Unit): Unit = 
        instance.onResponseStarted(filter.let { OnResponseStartedFilter().apply(it) }, listener)

    fun onBeforeRedirect(filter: OnBeforeRedirectFilter.() -> Unit, listener: (details: OnBeforeRedirectDetails) -> Unit): Unit = 
        instance.onBeforeRedirect(filter.let { OnBeforeRedirectFilter().apply(it) }, listener)

    fun onCompleted(filter: OnCompletedFilter.() -> Unit, listener: (details: OnCompletedDetails) -> Unit): Unit = 
        instance.onCompleted(filter.let { OnCompletedFilter().apply(it) }, listener)

    fun onErrorOccurred(filter: OnErrorOccurredFilter.() -> Unit, listener: (details: OnErrorOccurredDetails) -> Unit): Unit = 
        instance.onErrorOccurred(filter.let { OnErrorOccurredFilter().apply(it) }, listener)

    // ~ Builders -------------------------------------------------------------------------------

    class OnBeforeRequestFilter(
    )

    class OnBeforeRequestDetails(
        var id: Int, 
        var url: String, 
        var method: String, 
        var resourceType: String, 
        var timestamp: Double, 
        var uploadData: Array<UploadData>
    )

    class OnBeforeRequestResponse(
        var cancel: Boolean? = null, 
        var redirectURL: String? = null
    )

    class OnBeforeSendHeadersFilter(
    )

    class OnSendHeadersFilter(
    )

    class OnSendHeadersDetails(
        var id: Int, 
        var url: String, 
        var method: String, 
        var resourceType: String, 
        var timestamp: Double, 
        var requestHeaders: OnSendHeadersRequestHeaders
    )

    class OnSendHeadersRequestHeaders(
    )

    class OnHeadersReceivedFilter(
    )

    class OnResponseStartedFilter(
    )

    class OnResponseStartedDetails(
        var id: Int, 
        var url: String, 
        var method: String, 
        var resourceType: String, 
        var timestamp: Double, 
        var responseHeaders: OnResponseStartedResponseHeaders, 
        var fromCache: Boolean, 
        var statusCode: Int, 
        var statusLine: String
    )

    class OnResponseStartedResponseHeaders(
    )

    class OnBeforeRedirectFilter(
    )

    class OnBeforeRedirectDetails(
        var id: String, 
        var url: String, 
        var method: String, 
        var resourceType: String, 
        var timestamp: Double, 
        var redirectURL: String, 
        var statusCode: Int, 
        var ip: String? = null, 
        var fromCache: Boolean, 
        var responseHeaders: OnBeforeRedirectResponseHeaders
    )

    class OnBeforeRedirectResponseHeaders(
    )

    class OnCompletedFilter(
    )

    class OnCompletedDetails(
        var id: Int, 
        var url: String, 
        var method: String, 
        var resourceType: String, 
        var timestamp: Double, 
        var responseHeaders: OnCompletedResponseHeaders, 
        var fromCache: Boolean, 
        var statusCode: Int, 
        var statusLine: String
    )

    class OnCompletedResponseHeaders(
    )

    class OnErrorOccurredFilter(
    )

    class OnErrorOccurredDetails(
        var id: Int, 
        var url: String, 
        var method: String, 
        var resourceType: String, 
        var timestamp: Double, 
        var fromCache: Boolean, 
        var error: String
    )

}

