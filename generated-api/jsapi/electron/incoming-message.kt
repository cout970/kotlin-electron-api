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


    // ~ Companion -----------------------------------------------------------------------------

    companion object { 

        private val module: dynamic = js("require('electron').IncomingMessage")

    }

    // ~ Builders ------------------------------------------------------------------------------
}

