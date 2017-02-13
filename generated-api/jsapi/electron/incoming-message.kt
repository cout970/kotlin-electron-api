package jsapi.electron

class IncomingMessage() {

    val instance: dynamic

    init {
        val _constructor = js("require('electron').IncomingMessage")
        instance = js("new _constructor(_)")
    }

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Properties ----------------------------------------------------------------------------

    val statusCode: dynamic get() = instance.statusCode
    val statusMessage: dynamic get() = instance.statusMessage
    val headers: dynamic get() = instance.headers
    val httpVersion: dynamic get() = instance.httpVersion
    val httpVersionMajor: dynamic get() = instance.httpVersionMajor
    val httpVersionMinor: dynamic get() = instance.httpVersionMinor


    // ~ Companion -----------------------------------------------------------------------------

    companion object { 

        private val module: dynamic = js("require('electron').IncomingMessage")

    }

    // ~ Builders ------------------------------------------------------------------------------
}

