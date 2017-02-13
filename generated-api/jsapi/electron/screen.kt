package jsapi.electron

object screen {

    private val module: dynamic = js("require('electron').screen")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     *  . x Integer
     *
     *  . y Integer
     *
     * The current absolute position of the mouse pointer.
     */
    fun getCursorScreenPoint(x: Int, y: Int): dynamic = 
        module.getCursorScreenPoint(x, y)

    /**
     */
    fun getPrimaryDisplay(): Display = 
        module.getPrimaryDisplay()

    /**
     */
    fun getAllDisplays(): Array<Display> = 
        module.getAllDisplays()

    /**
     */
    fun getDisplayNearestPoint(point: GetDisplayNearestPointPoint): Display = 
        module.getDisplayNearestPoint(point)

    /**
     */
    fun getDisplayMatching(rect: Rectangle): Display = 
        module.getDisplayMatching(rect.instance)

    // ~ Builders ------------------------------------------------------------------------------

    class GetDisplayNearestPointPoint(
        var x: Int,
        var y: Int
    )
}

