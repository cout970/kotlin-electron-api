package jsapi.electron

class Tray(image: dynamic) {

    val instance: dynamic

    init {
        val _constructor = js("require('electron').Tray")
        val _image = image.instance
        instance = js("new _constructor(_image)")
    }

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Destroys the tray icon immediately.
     */
    fun destroy(): Unit = 
        instance.destroy()

    /**
     * Sets the image associated with this tray icon.
     */
    fun setImage(image: dynamic): Unit = 
        instance.setImage(image.instance)

    /**
     * Sets the image associated with this tray icon when pressed on macOS.
     */
    fun setPressedImage(image: NativeImage): Unit = 
        instance.setPressedImage(image.instance)

    /**
     * Sets the hover text for this tray icon.
     */
    fun setToolTip(toolTip: String): Unit = 
        instance.setToolTip(toolTip)

    /**
     * Sets the title displayed aside of the tray icon in the status bar.
     */
    fun setTitle(title: String): Unit = 
        instance.setTitle(title)

    /**
     * Sets when the tray's icon background becomes highlighted (in blue).
     *
     * Note: You can use highlightMode with a BrowserWindow by toggling between 'never' and 'always' modes when the window visibility changes.
     *
     */
    fun setHighlightMode(mode: String): Unit = 
        instance.setHighlightMode(mode)

    /**
     * Displays a tray balloon.
     */
    fun displayBalloon(options: DisplayBalloonOptions.() -> Unit): Unit = 
        instance.displayBalloon(options.let { DisplayBalloonOptions().apply(it) })

    /**
     * Pops up the context menu of the tray icon. When menu is passed, the menu will be shown instead of the tray icon's context menu.
     *
     * The position is only available on Windows, and it is (0, 0) by default.
     */
    fun popUpContextMenu(menu: Menu?, position: PopUpContextMenuPosition?): Unit = 
        instance.popUpContextMenu(menu?.instance, position)

    /**
     * Sets the context menu for this icon.
     */
    fun setContextMenu(menu: Menu): Unit = 
        instance.setContextMenu(menu.instance)

    /**
     * The bounds of this tray icon as Object.
     */
    fun getBounds(): Rectangle = 
        instance.getBounds()

    /**
     */
    fun isDestroyed(): Boolean = 
        instance.isDestroyed()

    // ~ Companion -----------------------------------------------------------------------------

    companion object { 

        private val module: dynamic = js("require('electron').Tray")

    }

    // ~ Builders ------------------------------------------------------------------------------

    class DisplayBalloonOptions(
        var icon: dynamic? = null,
        var title: String? = null,
        var content: String? = null
    )

    class PopUpContextMenuPosition(
        var x: Int,
        var y: Int
    )
}

