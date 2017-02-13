package jsapi.electron

object remote {

    private val module: dynamic = js("require('electron').remote")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     */
    fun getCurrentWindow(): BrowserWindow = 
        module.getCurrentWindow()

    /**
     */
    fun getCurrentWebContents(): WebContents = 
        module.getCurrentWebContents()

    /**
     */
    fun getGlobal(name: String): dynamic = 
        module.getGlobal(name)

    // ~ Builders ------------------------------------------------------------------------------
}

