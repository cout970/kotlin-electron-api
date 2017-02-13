package jsapi.electron

class DownloadItem() {

    val instance: dynamic

    init {
        val _constructor = js("require('electron').DownloadItem")
        instance = js("new _constructor(_)")
    }

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * The API is only available in session's will-download callback function. If 
     * user doesn't set the save path via the API, Electron will use the original 
     * routine to determine the save path(Usually prompts a save dialog).
     */
    fun setSavePath(path: String): Unit = 
        instance.setSavePath(path)

    /**
     */
    fun getSavePath(): String = 
        instance.getSavePath()

    /**
     * Pauses the download.
     */
    fun pause(): Unit = 
        instance.pause()

    /**
     */
    fun isPaused(): Boolean = 
        instance.isPaused()

    /**
     * Resumes the download that has been paused.
     */
    fun resume(): Unit = 
        instance.resume()

    /**
     * Resumes Boolean - Whether the download can resume.
     */
    fun canResume(): Unit = 
        instance.canResume()

    /**
     * Cancels the download operation.
     */
    fun cancel(): Unit = 
        instance.cancel()

    /**
     */
    fun getURL(): String = 
        instance.getURL()

    /**
     */
    fun getMimeType(): String = 
        instance.getMimeType()

    /**
     */
    fun hasUserGesture(): Boolean = 
        instance.hasUserGesture()

    /**
     * Note: The file name is not always the same as the actual one saved in local 
     * disk. If user changes the file name in a prompted download saving dialog, the 
     * actual name of saved file will be different.
     */
    fun getFilename(): String = 
        instance.getFilename()

    /**
     * If the size is unknown, it returns 0.
     */
    fun getTotalBytes(): Int = 
        instance.getTotalBytes()

    /**
     */
    fun getReceivedBytes(): Int = 
        instance.getReceivedBytes()

    /**
     */
    fun getContentDisposition(): String = 
        instance.getContentDisposition()

    /**
     * Note: The following methods are useful specifically to resume a cancelled item 
     * when session is restarted.
     */
    fun getState(): String = 
        instance.getState()

    /**
     */
    fun getURLChain(): Array<String> = 
        instance.getURLChain()

    /**
     */
    fun getLastModifiedTime(): String = 
        instance.getLastModifiedTime()

    /**
     */
    fun getETag(): String = 
        instance.getETag()

    /**
     */
    fun getStartTime(): Double = 
        instance.getStartTime()

    // ~ Companion -----------------------------------------------------------------------------

    companion object { 

        private val module: dynamic = js("require('electron').DownloadItem")

    }

    // ~ Builders ------------------------------------------------------------------------------
}

