@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

object screen {

    private val module: dynamic = js("require('electron').screen")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     *  . x Integer
     *  . y Integer
     *
     * The current absolute position of the mouse pointer.
     */
    fun getCursorScreenPoint(x: Int, y: Int): dynamic = 
        module.getCursorScreenPoint(x, y)

    /**
     * @return The primary display.
     */
    fun getPrimaryDisplay(): Display = 
        module.getPrimaryDisplay()

    /**
     * @return An array of displays that are currently available.
     */
    fun getAllDisplays(): Array<Display> = 
        module.getAllDisplays()

    /**
     * @return The display nearest the specified point.
     */
    fun getDisplayNearestPoint(point: GetDisplayNearestPointPoint): Display = 
        module.getDisplayNearestPoint(point)

    /**
     * @return The display that most closely intersects the provided bounds.
     */
    fun getDisplayMatching(rect: Rectangle): Display = 
        module.getDisplayMatching(rect.instance)

    // ~ Builders ------------------------------------------------------------------------------

    class GetDisplayNearestPointPoint(
        var x: Int,
        var y: Int
    )
}

