package jsapi.electron

object desktopCapturer {

    private val module: dynamic = js("require('electron').desktopCapturer")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

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

