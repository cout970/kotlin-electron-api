@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

object remote {

    private val module: dynamic = js("require('electron').remote")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * @return The window to which this web page belongs.
     */
    fun getCurrentWindow(): BrowserWindow = 
        module.getCurrentWindow()

    /**
     * @return The web contents of this web page.
     */
    fun getCurrentWebContents(): WebContents = 
        module.getCurrentWebContents()

    /**
     * @return The global variable of name (e.g. global[name]) in the main process.
     */
    fun getGlobal(name: String): dynamic = 
        module.getGlobal(name)

    // ~ Builders ------------------------------------------------------------------------------
}

