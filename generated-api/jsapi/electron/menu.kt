package jsapi.electron

class Menu() {

    private val module: dynamic = js("require('electron').Menu")

    val instance: dynamic

    init {
        val _constructor = js("require('electron').Menu")
        instance = js("new _constructor(_)")
    }

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun setApplicationMenu(menu: Menu): Unit = 
        module.setApplicationMenu(menu.instance)

    fun getApplicationMenu(): Menu = 
        module.getApplicationMenu()

    fun sendActionToFirstResponder(action: String): Unit = 
        module.sendActionToFirstResponder(action)

    fun buildFromTemplate(template: Array<dynamic>): Menu = 
        module.buildFromTemplate(template)

    fun popup(browserWindow: BrowserWindow?, x: Float?, y: Float, positioningItem: Float?): Unit = 
        instance.popup(browserWindow?.instance, x, y, positioningItem)

    fun append(menuItem: MenuItem): Unit = 
        instance.append(menuItem.instance)

    fun insert(pos: Int, menuItem: MenuItem): Unit = 
        instance.insert(pos, menuItem.instance)

    // ~ Builders -------------------------------------------------------------------------------

}

