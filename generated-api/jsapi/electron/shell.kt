package jsapi.electron

object shell {

    private val module: dynamic = js("require('electron').shell")

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun showItemInFolder(fullPath: String): Boolean = 
        module.showItemInFolder(fullPath)

    fun openItem(fullPath: String): Boolean = 
        module.openItem(fullPath)

    fun openExternal(url: String, options: OpenExternalOptions?, callback: ((error: Error) -> Unit)?): Boolean = 
        module.openExternal(url, options, callback)

    fun moveItemToTrash(fullPath: String): Boolean = 
        module.moveItemToTrash(fullPath)

    fun beep(): Unit = 
        module.beep()

    fun writeShortcutLink(shortcutPath: String, operation: String?, options: ShortcutDetails): Boolean = 
        module.writeShortcutLink(shortcutPath, operation, options.instance)

    fun readShortcutLink(shortcutPath: String): ShortcutDetails = 
        module.readShortcutLink(shortcutPath)

    // ~ Builders -------------------------------------------------------------------------------

    class OpenExternalOptions(
        var activate: Boolean
    )

}

