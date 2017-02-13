package jsapi.electron

object autoUpdater {

    private val module: dynamic = js("require('electron').autoUpdater")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Sets the url and initialize the auto updater.
     */
    fun setFeedURL(url: String, requestHeaders: (SetFeedURLRequestHeaders.() -> Unit)?): Unit = 
        module.setFeedURL(url, requestHeaders?.let { SetFeedURLRequestHeaders().apply(it) })

    /**
     */
    fun getFeedURL(): String = 
        module.getFeedURL()

    /**
     * Asks the server whether there is an update. You must call setFeedURL before using this API.
     */
    fun checkForUpdates(): Unit = 
        module.checkForUpdates()

    /**
     * Restarts the app and installs the update after it has been downloaded. It should only be called after update-downloaded has been emitted.
     *
     * Note:autoUpdater.quitAndInstall() will close all application windows first and only emit before-quit event on app after that. This is different from the normal quit event sequence.
     */
    fun quitAndInstall(): Unit = 
        module.quitAndInstall()

    // ~ Builders ------------------------------------------------------------------------------

    class SetFeedURLRequestHeaders(
    )
}

