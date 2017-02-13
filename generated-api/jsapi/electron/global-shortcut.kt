package jsapi.electron

object globalShortcut {

    private val module: dynamic = js("require('electron').globalShortcut")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Registers a global shortcut of accelerator. The callback is called when the 
     * registered shortcut is pressed by the user.
     *
     * When the accelerator is already taken by other applications, this call will 
     * silently fail. This behavior is intended by operating systems, since they 
     * don't want applications to fight for global shortcuts.
     */
    fun register(accelerator: dynamic, callback: () -> Unit): Unit = 
        module.register(accelerator.instance, callback)

    /**
     * When the accelerator is already taken by other applications, this call will 
     * still return false. This behavior is intended by operating systems, since they 
     * don't want applications to fight for global shortcuts.
     */
    fun isRegistered(accelerator: dynamic): Boolean = 
        module.isRegistered(accelerator.instance)

    /**
     * Unregisters the global shortcut of accelerator.
     */
    fun unregister(accelerator: dynamic): Unit = 
        module.unregister(accelerator.instance)

    /**
     * Unregisters all of the global shortcuts.
     */
    fun unregisterAll(): Unit = 
        module.unregisterAll()

    // ~ Builders ------------------------------------------------------------------------------
}

