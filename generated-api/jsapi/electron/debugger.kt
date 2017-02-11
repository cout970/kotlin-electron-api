package jsapi.electron

class Debugger() {

    private val module: dynamic = js("require('electron').Debugger")

    val instance: dynamic

    init {
        val _constructor = js("require('electron').Debugger")
        instance = js("new _constructor(_)")
    }

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun attach(protocolVersion: String?): Unit = 
        instance.attach(protocolVersion)

    fun isAttached(): Boolean = 
        instance.isAttached()

    fun detach(): Unit = 
        instance.detach()

    fun sendCommand(method: String, commandParams: (SendCommandCommandParams.() -> Unit)?, callback: ((error: SendCommandError.() -> Unit, result: Any) -> Unit)?): Unit = 
        instance.sendCommand(method, commandParams?.let { SendCommandCommandParams().apply(it) }, callback)

    // ~ Builders -------------------------------------------------------------------------------

    class SendCommandCommandParams(
    )

    class SendCommandError(
    )

}

