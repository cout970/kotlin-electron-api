@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

@Suppress("REDUNDANT_NULLABLE")
object powerMonitor {

    private val module: dynamic = js("require('electron').powerMonitor")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit): Unit = 
        module.on(event, callback)


    // ~ Builders ------------------------------------------------------------------------------
}

