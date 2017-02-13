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

    fun setSavePath(path: String): Unit = 
        instance.setSavePath(path)

    fun getSavePath(): String = 
        instance.getSavePath()

    fun pause(): Unit = 
        instance.pause()

    fun isPaused(): Boolean = 
        instance.isPaused()

    fun resume(): Unit = 
        instance.resume()

    fun canResume(): Unit = 
        instance.canResume()

    fun cancel(): Unit = 
        instance.cancel()

    fun getURL(): String = 
        instance.getURL()

    fun getMimeType(): String = 
        instance.getMimeType()

    fun hasUserGesture(): Boolean = 
        instance.hasUserGesture()

    fun getFilename(): String = 
        instance.getFilename()

    fun getTotalBytes(): Int = 
        instance.getTotalBytes()

    fun getReceivedBytes(): Int = 
        instance.getReceivedBytes()

    fun getContentDisposition(): String = 
        instance.getContentDisposition()

    fun getState(): String = 
        instance.getState()

    fun getURLChain(): Array<String> = 
        instance.getURLChain()

    fun getLastModifiedTime(): String = 
        instance.getLastModifiedTime()

    fun getETag(): String = 
        instance.getETag()

    fun getStartTime(): Double = 
        instance.getStartTime()

    // ~ Companion -----------------------------------------------------------------------------

    companion object { 

        private val module: dynamic = js("require('electron').DownloadItem")

    }

    // ~ Builders ------------------------------------------------------------------------------
}

