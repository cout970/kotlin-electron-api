@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

class Debugger constructor(val instance: dynamic, z: Unit) {

    constructor() : this(Unit.let {
        val _constructor = js("require('electron').Debugger")
        js("new _constructor()")
    }, z = Unit)

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
     * @return Whether a debugger is attached to the webContents.
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

