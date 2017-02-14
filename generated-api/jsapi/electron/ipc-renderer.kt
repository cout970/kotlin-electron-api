@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

object ipcRenderer {

    private val module: dynamic = js("require('electron').ipcRenderer")

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

    /**
     * Send a message to the main process asynchronously via channel, you can also 
     * send arbitrary arguments. Arguments will be serialized in JSON internally and 
     * hence no functions or prototype chain will be included.
     *
     * The main process handles it by listening for channel with ipcMain module.
     */
    fun send(channel: String, args: Array<dynamic>): Unit = 
        module.send(channel, args)

    /**
     * Send a message to the main process synchronously via channel, you can also 
     * send arbitrary arguments. Arguments will be serialized in JSON internally and 
     * hence no functions or prototype chain will be included.
     *
     * The main process handles it by listening for channel with ipcMain module, and 
     * replies by setting event.returnValue.
     *
     * Note: Sending a synchronous message will block the whole renderer process, 
     * unless you know what you are doing you should never use it.
     */
    fun sendSync(channel: String, args: Array<dynamic>): Unit = 
        module.sendSync(channel, args)

    /**
     * Like ipcRenderer.send but the event will be sent to the <webview> element in 
     * the host page instead of the main process.
     */
    fun sendToHost(channel: String, args: Array<dynamic>): Unit = 
        module.sendToHost(channel, args)

    // ~ Builders ------------------------------------------------------------------------------
}

