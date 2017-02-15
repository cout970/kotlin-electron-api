@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

class DownloadItem constructor(val instance: dynamic, z: Unit) {

    constructor() : this(Unit.let {
        val _constructor = js("require('electron').DownloadItem")
        js("new _constructor()")
    }, z = Unit)

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
     * @return The save path of the download item. This will be either the path set via 
     * downloadItem.setSavePath(path) or the path selected from the shown save dialog.
     */
    fun getSavePath(): String = 
        instance.getSavePath()

    /**
     * Pauses the download.
     */
    fun pause(): Unit = 
        instance.pause()

    /**
     * @return Whether the download is paused.
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
     * @return The origin url where the item is downloaded from.
     */
    fun getURL(): String = 
        instance.getURL()

    /**
     * @return The files mime type.
     */
    fun getMimeType(): String = 
        instance.getMimeType()

    /**
     * @return Whether the download has user gesture.
     */
    fun hasUserGesture(): Boolean = 
        instance.hasUserGesture()

    /**
     * Note: The file name is not always the same as the actual one saved in local 
     * disk. If user changes the file name in a prompted download saving dialog, the 
     * actual name of saved file will be different.
     * 
     * @return The file name of the download item.
     */
    fun getFilename(): String = 
        instance.getFilename()

    /**
     * If the size is unknown, it returns 0.
     * 
     * @return The total size in bytes of the download item.
     */
    fun getTotalBytes(): Int = 
        instance.getTotalBytes()

    /**
     * @return The received bytes of the download item.
     */
    fun getReceivedBytes(): Int = 
        instance.getReceivedBytes()

    /**
     * @return The Content-Disposition field from the response header.
     */
    fun getContentDisposition(): String = 
        instance.getContentDisposition()

    /**
     * Note: The following methods are useful specifically to resume a cancelled item 
     * when session is restarted.
     * 
     * @return The current state. Can be progressing, completed, cancelled or interrupted.
     */
    fun getState(): String = 
        instance.getState()

    /**
     * @return The complete url chain of the item including any redirects.
     */
    fun getURLChain(): Array<String> = 
        instance.getURLChain()

    /**
     * @return Last-Modified header value.
     */
    fun getLastModifiedTime(): String = 
        instance.getLastModifiedTime()

    /**
     * @return ETag header value.
     */
    fun getETag(): String = 
        instance.getETag()

    /**
     * @return Number of seconds since the UNIX epoch when the download was started.
     */
    fun getStartTime(): Double = 
        instance.getStartTime()

    // ~ Companion -----------------------------------------------------------------------------

    companion object { 

        private val module: dynamic = js("require('electron').DownloadItem")

    }

    // ~ Builders ------------------------------------------------------------------------------
}

