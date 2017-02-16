@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

@Suppress("REDUNDANT_NULLABLE")
object desktopCapturer {

    private val module: dynamic = js("require('electron').desktopCapturer")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit): Unit = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Starts gathering information about all available desktop media sources, and 
     * calls callback(error, sources) when finished.
     *
     * sources is an array of DesktopCapturerSource objects, each 
     * DesktopCapturerSource represents a screen or an individual window that can be 
     * captured.
     * 
         * @param options 
     * @param callback 
     */
    fun getSources(options: GetSourcesOptions, callback: (error: Error, sources: Array<DesktopCapturerSource>) -> Unit): Unit = 
        module.getSources(options, callback)

    // ~ Builders ------------------------------------------------------------------------------

    class GetSourcesOptions(
        /**
         * An array of Strings that lists the types of desktop sources to be captured, 
         * available types are screen and window.
         */
        var types: Array<String>,

        /**
         * The suggested size that the media source thumbnail should be scaled to, 
         * defaults to {width: 150, height: 150}.
         */
        var thumbnailSize: GetSourcesThumbnailSize? = null

    )
    class GetSourcesThumbnailSize(
    )

}

