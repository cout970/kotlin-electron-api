package jsapi.electron

object protocol {

    private val module: dynamic = js("require('electron').protocol")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun registerStandardSchemes(schemes: Array<String>, options: (RegisterStandardSchemesOptions.() -> Unit)?): Unit = 
        module.registerStandardSchemes(schemes, options?.let { RegisterStandardSchemesOptions().apply(it) })

    fun registerServiceWorkerSchemes(schemes: Array<String>): Unit = 
        module.registerServiceWorkerSchemes(schemes)

    fun registerFileProtocol(scheme: String, handler: (request: RegisterFileProtocolRequest, callback: (filePath: String?) -> Unit) -> Unit, completion: ((error: Error) -> Unit)?): Unit = 
        module.registerFileProtocol(scheme, handler, completion)

    fun registerBufferProtocol(scheme: String, handler: (request: RegisterBufferProtocolRequest, callback: (buffer: dynamic?) -> Unit) -> Unit, completion: ((error: Error) -> Unit)?): Unit = 
        module.registerBufferProtocol(scheme, handler, completion)

    fun registerStringProtocol(scheme: String, handler: (request: RegisterStringProtocolRequest, callback: (data: String?) -> Unit) -> Unit, completion: ((error: Error) -> Unit)?): Unit = 
        module.registerStringProtocol(scheme, handler, completion)

    fun registerHttpProtocol(scheme: String, handler: (request: RegisterHttpProtocolRequest, callback: (redirectRequest: RegisterHttpProtocolRedirectRequest) -> Unit) -> Unit, completion: ((error: Error) -> Unit)?): Unit = 
        module.registerHttpProtocol(scheme, handler, completion)

    fun unregisterProtocol(scheme: String, completion: ((error: Error) -> Unit)?): Unit = 
        module.unregisterProtocol(scheme, completion)

    fun isProtocolHandled(scheme: String, callback: (error: Error) -> Unit): Unit = 
        module.isProtocolHandled(scheme, callback)

    fun interceptFileProtocol(scheme: String, handler: (request: InterceptFileProtocolRequest, callback: (filePath: String) -> Unit) -> Unit, completion: ((error: Error) -> Unit)?): Unit = 
        module.interceptFileProtocol(scheme, handler, completion)

    fun interceptStringProtocol(scheme: String, handler: (request: InterceptStringProtocolRequest, callback: (data: String?) -> Unit) -> Unit, completion: ((error: Error) -> Unit)?): Unit = 
        module.interceptStringProtocol(scheme, handler, completion)

    fun interceptBufferProtocol(scheme: String, handler: (request: InterceptBufferProtocolRequest, callback: (buffer: dynamic?) -> Unit) -> Unit, completion: ((error: Error) -> Unit)?): Unit = 
        module.interceptBufferProtocol(scheme, handler, completion)

    fun interceptHttpProtocol(scheme: String, handler: (request: InterceptHttpProtocolRequest, callback: (redirectRequest: InterceptHttpProtocolRedirectRequest) -> Unit) -> Unit, completion: ((error: Error) -> Unit)?): Unit = 
        module.interceptHttpProtocol(scheme, handler, completion)

    fun uninterceptProtocol(scheme: String, completion: ((error: Error) -> Unit)?): Unit = 
        module.uninterceptProtocol(scheme, completion)

    // ~ Builders ------------------------------------------------------------------------------

    class RegisterStandardSchemesOptions(
        var secure: Boolean? = null
    )

    class RegisterFileProtocolRequest(
        var url: String,
        var referrer: String,
        var method: String,
        var uploadData: Array<UploadData>
    )

    class RegisterBufferProtocolRequest(
        var url: String,
        var referrer: String,
        var method: String,
        var uploadData: Array<UploadData>
    )

    class RegisterStringProtocolRequest(
        var url: String,
        var referrer: String,
        var method: String,
        var uploadData: Array<UploadData>
    )

    class RegisterHttpProtocolRequest(
        var url: String,
        var referrer: String,
        var method: String,
        var uploadData: Array<UploadData>
    )

    class RegisterHttpProtocolRedirectRequest(
        var url: String,
        var method: String,
        var session: (RegisterHttpProtocolSession.() -> Unit)? = null,
        var uploadData: RegisterHttpProtocolUploadData? = null
    )
    class RegisterHttpProtocolSession(
    )

    class RegisterHttpProtocolUploadData(
        var contentType: String,
        var data: String
    )


    class InterceptFileProtocolRequest(
        var url: String,
        var referrer: String,
        var method: String,
        var uploadData: Array<UploadData>
    )

    class InterceptStringProtocolRequest(
        var url: String,
        var referrer: String,
        var method: String,
        var uploadData: Array<UploadData>
    )

    class InterceptBufferProtocolRequest(
        var url: String,
        var referrer: String,
        var method: String,
        var uploadData: Array<UploadData>
    )

    class InterceptHttpProtocolRequest(
        var url: String,
        var referrer: String,
        var method: String,
        var uploadData: Array<UploadData>
    )

    class InterceptHttpProtocolRedirectRequest(
        var url: String,
        var method: String,
        var session: (InterceptHttpProtocolSession.() -> Unit)? = null,
        var uploadData: InterceptHttpProtocolUploadData? = null
    )
    class InterceptHttpProtocolSession(
    )

    class InterceptHttpProtocolUploadData(
        var contentType: String,
        var data: String
    )

}

