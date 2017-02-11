package jsapi.electron

object globalShortcut {

    private val module: dynamic = js("require('electron').globalShortcut")

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun register(accelerator: dynamic, callback: () -> Unit): Unit = 
        module.register(accelerator.instance, callback)

    fun isRegistered(accelerator: dynamic): Boolean = 
        module.isRegistered(accelerator.instance)

    fun unregister(accelerator: dynamic): Unit = 
        module.unregister(accelerator.instance)

    fun unregisterAll(): Unit = 
        module.unregisterAll()

    // ~ Builders -------------------------------------------------------------------------------

}

