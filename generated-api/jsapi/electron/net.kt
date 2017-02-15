@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

object net {

    private val module: dynamic = js("require('electron').net")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Creates a ClientRequest instance using the provided options which are directly 
     * forwarded to the ClientRequest constructor. The net.request method would be 
     * used to issue both secure and insecure HTTP requests according to the 
     * specified protocol scheme in the options object.
     */
    fun request(options: dynamic): ClientRequest = 
        module.request(options)

    // ~ Builders ------------------------------------------------------------------------------
}

