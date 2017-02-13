package jsapi.electron

object ipcMain {

    private val module: dynamic = js("require('electron').ipcMain")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun on(channel: String, listener: () -> Unit): Unit = 
        module.on(channel, listener)

    fun once(channel: String, listener: () -> Unit): Unit = 
        module.once(channel, listener)

    fun removeListener(channel: String, listener: () -> Unit): Unit = 
        module.removeListener(channel, listener)

    fun removeAllListeners(channel: String?): Unit = 
        module.removeAllListeners(channel)

    // ~ Builders ------------------------------------------------------------------------------
}

