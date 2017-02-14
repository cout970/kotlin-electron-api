package jsapi.electron

class Debugger() {

    val instance: dynamic

    init {
        val _constructor = js("require('electron').Debugger")
        instance = js("new _constructor()")
    }

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Attaches the debugger to the webContents.
     */
    fun attach(protocolVersion: String?): Unit = 
        instance.attach(protocolVersion)

    /**
     */
    fun isAttached(): Boolean = 
        instance.isAttached()

    /**
     * Detaches the debugger from the webContents.
     */
    fun detach(): Unit = 
        instance.detach()

    /**
     * Send given command to the debugging target.
     */
    fun sendCommand(method: String, commandParams: (SendCommandCommandParams.() -> Unit)?, callback: ((error: SendCommandError.() -> Unit, result: Any) -> Unit)?): Unit = 
        instance.sendCommand(method, commandParams?.let { SendCommandCommandParams().apply(it) }, callback)

    // ~ Companion -----------------------------------------------------------------------------

    companion object { 

        private val module: dynamic = js("require('electron').Debugger")

    }

    // ~ Builders ------------------------------------------------------------------------------

    class SendCommandCommandParams(
    )

    class SendCommandError(
    )
}

