@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

object shell {

    private val module: dynamic = js("require('electron').shell")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Show the given file in a file manager. If possible, select the file.
     * 
     * @return Whether the item was successfully shown
     */
    fun showItemInFolder(fullPath: String): Boolean = 
        module.showItemInFolder(fullPath)

    /**
     * Open the given file in the desktop's default manner.
     * 
     * @return Whether the item was successfully opened.
     */
    fun openItem(fullPath: String): Boolean = 
        module.openItem(fullPath)

    /**
     * Open the given external protocol URL in the desktop's default manner. (For 
     * example, mailto: URLs in the user's default mail agent).
     * 
     * @return Whether an application was available to open the URL. If callback is 
     * specified, always returns true.
     */
    fun openExternal(url: String, options: OpenExternalOptions?, callback: ((error: Error) -> Unit)?): Boolean = 
        module.openExternal(url, options, callback)

    /**
     * Move the given file to trash and returns a boolean status for the operation.
     * 
     * @return Whether the item was successfully moved to the trash
     */
    fun moveItemToTrash(fullPath: String): Boolean = 
        module.moveItemToTrash(fullPath)

    /**
     * Play the beep sound.
     */
    fun beep(): Unit = 
        module.beep()

    /**
     * Creates or updates a shortcut link at shortcutPath.
     * 
     * @return Whether the shortcut was created successfully
     */
    fun writeShortcutLink(shortcutPath: String, operation: String?, options: ShortcutDetails): Boolean = 
        module.writeShortcutLink(shortcutPath, operation, options.instance)

    /**
     * Resolves the shortcut link at shortcutPath.
     *
     * An exception will be thrown when any error happens.
     */
    fun readShortcutLink(shortcutPath: String): ShortcutDetails = 
        module.readShortcutLink(shortcutPath)

    // ~ Builders ------------------------------------------------------------------------------

    class OpenExternalOptions(
        var activate: Boolean
    )
}

