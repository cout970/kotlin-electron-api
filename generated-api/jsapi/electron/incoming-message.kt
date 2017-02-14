package jsapi.electron

class IncomingMessage() {

    val instance: dynamic

    init {
        val _constructor = js("require('electron').IncomingMessage")
        instance = js("new _constructor()")
    }

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Properties ----------------------------------------------------------------------------

    /**
     * An Integer indicating the HTTP response status code.
     */
    val statusCode: dynamic get() = instance.statusCode

    /**
     * A String representing the HTTP status message.
     */
    val statusMessage: dynamic get() = instance.statusMessage

    /**
     * An Object representing the response HTTP headers. The headers object is 
     * formatted as follows:
     *
 *  . All header names are lowercased.
 *  . Each header name produces an array-valued property on the headers object.
 *  . Each header value is pushed into the array associated with its header name.
 *
     */
    val headers: dynamic get() = instance.headers

    /**
     * A String indicating the HTTP protocol version number. Typical values are '1.0' 
     * or '1.1'. Additionally httpVersionMajor and httpVersionMinor are two 
     * Integer-valued readable properties that return respectively the HTTP major and 
     * minor version numbers.
     */
    val httpVersion: dynamic get() = instance.httpVersion

    /**
     * An Integer indicating the HTTP protocol major version number.
     */
    val httpVersionMajor: dynamic get() = instance.httpVersionMajor

    /**
     * An Integer indicating the HTTP protocol minor version number.
     */
    val httpVersionMinor: dynamic get() = instance.httpVersionMinor



    // ~ Companion -----------------------------------------------------------------------------

    companion object { 

        private val module: dynamic = js("require('electron').IncomingMessage")

    }

    // ~ Builders ------------------------------------------------------------------------------
}

