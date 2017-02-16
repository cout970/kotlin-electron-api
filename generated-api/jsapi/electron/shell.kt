@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

@Suppress("REDUNDANT_NULLABLE")
object shell {

    private val module: dynamic = js("require('electron').shell")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit): Unit = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Show the given file in a file manager. If possible, select the file.
     * 
     * @param fullPath 
     *
     * @returns Whether the item was successfully shown
     */
    fun showItemInFolder(fullPath: String): Boolean = 
        module.showItemInFolder(fullPath)

    /**
     * Open the given file in the desktop's default manner.
     * 
     * @param fullPath 
     *
     * @returns Whether the item was successfully opened.
     */
    fun openItem(fullPath: String): Boolean = 
        module.openItem(fullPath)

    /**
     * Open the given external protocol URL in the desktop's default manner. (For 
     * example, mailto: URLs in the user's default mail agent).
     * 
     * @param url 
         * @param options 
     * @param callback If specified will perform the open asynchronously. macOS
     *
     * @returns Whether an application was available to open the URL. If callback is 
     *          specified, always returns true.
     */
    fun openExternal(url: String, options: OpenExternalOptions? = null, callback: ((error: Error) -> Unit)? = null): Boolean = 
        module.openExternal(url, options, callback)

    /**
     * Move the given file to trash and returns a boolean status for the operation.
     * 
     * @param fullPath 
     *
     * @returns Whether the item was successfully moved to the trash
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
     * @param shortcutPath 
     * @param operation Default is create, can be one of following:
     * @param options 
     *
     * @returns Whether the shortcut was created successfully
     */
    fun writeShortcutLink(shortcutPath: String, operation: String? = null, options: ShortcutDetails): Boolean = 
        module.writeShortcutLink(shortcutPath, operation, options.instance)

    /**
     * Resolves the shortcut link at shortcutPath.
     *
     * An exception will be thrown when any error happens.
     * 
     * @param shortcutPath 
     *
     * @returns 
     */
    fun readShortcutLink(shortcutPath: String): ShortcutDetails = 
        module.readShortcutLink(shortcutPath)

    // ~ Builders ------------------------------------------------------------------------------

    class OpenExternalOptions(
        /**
         * true to bring the opened application to the foreground. The default is true.
         */
        var activate: Boolean

    )
}

