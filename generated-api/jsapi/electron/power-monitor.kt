package jsapi.electron

object powerMonitor {

    private val module: dynamic = js("require('electron').powerMonitor")

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Builders -------------------------------------------------------------------------------

}

