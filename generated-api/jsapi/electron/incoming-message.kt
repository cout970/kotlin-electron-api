package jsapi.electron

class IncomingMessage() {

    private val module: dynamic = js("require('electron').IncomingMessage")

    val instance: dynamic

    init {
        val _constructor = js("require('electron').IncomingMessage")
        instance = js("new _constructor(_)")
    }

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Builders -------------------------------------------------------------------------------

}

