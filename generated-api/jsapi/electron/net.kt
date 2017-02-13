package jsapi.electron

object net {

    private val module: dynamic = js("require('electron').net")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun request(options: dynamic): ClientRequest = 
        module.request(options.instance)

    // ~ Builders ------------------------------------------------------------------------------
}

