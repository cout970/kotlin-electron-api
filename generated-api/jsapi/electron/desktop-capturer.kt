package jsapi.electron

object desktopCapturer {

    private val module: dynamic = js("require('electron').desktopCapturer")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Starts gathering information about all available desktop media sources, and calls callback(error, sources) when finished.
     *
     * sources is an array of DesktopCapturerSource objects, each DesktopCapturerSource represents a screen or an individual window that can be captured.
     */
    fun getSources(options: GetSourcesOptions, callback: (error: Error, sources: Array<DesktopCapturerSource>) -> Unit): Unit = 
        module.getSources(options, callback)

    // ~ Builders ------------------------------------------------------------------------------

    class GetSourcesOptions(
        var types: Array<String>,
        var thumbnailSize: (GetSourcesThumbnailSize.() -> Unit)? = null
    )
    class GetSourcesThumbnailSize(
    )

}

