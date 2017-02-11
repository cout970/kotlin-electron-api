package jsapi.electron

object autoUpdater {

    private val module: dynamic = js("require('electron').autoUpdater")

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun setFeedURL(url: String, requestHeaders: (SetFeedURLRequestHeaders.() -> Unit)?): Unit = 
        module.setFeedURL(url, requestHeaders?.let { SetFeedURLRequestHeaders().apply(it) })

    fun getFeedURL(): String = 
        module.getFeedURL()

    fun checkForUpdates(): Unit = 
        module.checkForUpdates()

    fun quitAndInstall(): Unit = 
        module.quitAndInstall()

    // ~ Builders -------------------------------------------------------------------------------

    class SetFeedURLRequestHeaders(
    )

}

