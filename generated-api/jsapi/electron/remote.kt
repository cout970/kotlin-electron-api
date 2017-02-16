@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

@Suppress("REDUNDANT_NULLABLE")
object remote {

    private val module: dynamic = js("require('electron').remote")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit): Unit = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * @returns The window to which this web page belongs.
     */
    fun getCurrentWindow(): BrowserWindow = 
        module.getCurrentWindow()

    /**
     * @returns The web contents of this web page.
     */
    fun getCurrentWebContents(): WebContents = 
        module.getCurrentWebContents()

    /**
     * @param name 
     *
     * @returns The global variable of name (e.g. global[name]) in the main process.
     */
    fun getGlobal(name: String): dynamic = 
        module.getGlobal(name)

    // ~ Builders ------------------------------------------------------------------------------
}

