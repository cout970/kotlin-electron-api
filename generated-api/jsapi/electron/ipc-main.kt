package jsapi.electron

object ipcMain {

    private val module: dynamic = js("require('electron').ipcMain")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Listens to channel, when a new message arrives listener would be called with 
     * listener(event, args...).
     */
    fun on(channel: String, listener: () -> Unit): Unit = 
        module.on(channel, listener)

    /**
     * Adds a one time listener function for the event. This listener is invoked only 
     * the next time a message is sent to channel, after which it is removed.
     */
    fun once(channel: String, listener: () -> Unit): Unit = 
        module.once(channel, listener)

    /**
     * Removes the specified listener from the listener array for the specified 
     * channel.
     */
    fun removeListener(channel: String, listener: () -> Unit): Unit = 
        module.removeListener(channel, listener)

    /**
     * Removes all listeners, or those of the specified channel.
     */
    fun removeAllListeners(channel: String?): Unit = 
        module.removeAllListeners(channel)

    // ~ Builders ------------------------------------------------------------------------------
}

