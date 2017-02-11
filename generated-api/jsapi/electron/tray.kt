package jsapi.electron

class Tray(image: dynamic) {

    private val module: dynamic = js("require('electron').Tray")

    val instance: dynamic

    init {
        val _constructor = js("require('electron').Tray")
        val _image = image
        instance = js("new _constructor(_image)")
    }

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun destroy(): Unit = 
        instance.destroy()

    fun setImage(image: dynamic): Unit = 
        instance.setImage(image.instance)

    fun setPressedImage(image: NativeImage): Unit = 
        instance.setPressedImage(image.instance)

    fun setToolTip(toolTip: String): Unit = 
        instance.setToolTip(toolTip)

    fun setTitle(title: String): Unit = 
        instance.setTitle(title)

    fun setHighlightMode(mode: String): Unit = 
        instance.setHighlightMode(mode)

    fun displayBalloon(options: DisplayBalloonOptions.() -> Unit): Unit = 
        instance.displayBalloon(options.let { DisplayBalloonOptions().apply(it) })

    fun popUpContextMenu(menu: Menu?, position: PopUpContextMenuPosition?): Unit = 
        instance.popUpContextMenu(menu?.instance, position)

    fun setContextMenu(menu: Menu): Unit = 
        instance.setContextMenu(menu.instance)

    fun getBounds(): Rectangle = 
        instance.getBounds()

    fun isDestroyed(): Boolean = 
        instance.isDestroyed()

    // ~ Builders -------------------------------------------------------------------------------

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

