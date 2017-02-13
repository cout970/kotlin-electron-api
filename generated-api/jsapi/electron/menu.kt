package jsapi.electron

class Menu() {

    val instance: dynamic

    init {
        val _constructor = js("require('electron').Menu")
        instance = js("new _constructor(_)")
    }

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun popup(browserWindow: BrowserWindow?, x: Number?, y: Number, positioningItem: Number?): Unit = 
        instance.popup(browserWindow?.instance, x, y, positioningItem)

    fun append(menuItem: MenuItem): Unit = 
        instance.append(menuItem.instance)

    fun insert(pos: Int, menuItem: MenuItem): Unit = 
        instance.insert(pos, menuItem.instance)

    // ~ Companion -----------------------------------------------------------------------------

    companion object { 

        private val module: dynamic = js("require('electron').Menu")

        // ~ Methods -------------------------------------------------------------------------------

        fun setApplicationMenu(menu: Menu): Unit = 
            module.setApplicationMenu(menu.instance)

        fun getApplicationMenu(): Menu = 
            module.getApplicationMenu()

        fun sendActionToFirstResponder(action: String): Unit = 
            module.sendActionToFirstResponder(action)

        fun buildFromTemplate(template: Array<dynamic>): Menu = 
            module.buildFromTemplate(template)
    }

    // ~ Builders ------------------------------------------------------------------------------
}

