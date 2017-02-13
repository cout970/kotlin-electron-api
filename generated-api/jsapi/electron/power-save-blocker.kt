package jsapi.electron

object powerSaveBlocker {

    private val module: dynamic = js("require('electron').powerSaveBlocker")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun start(type: String): Int = 
        module.start(type)

    fun stop(id: Int): Unit = 
        module.stop(id)

    fun isStarted(id: Int): Boolean = 
        module.isStarted(id)

    // ~ Builders ------------------------------------------------------------------------------
}

