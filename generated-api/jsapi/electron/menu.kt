@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

class Menu constructor(val instance: dynamic, z: Unit) {

    constructor() : this(Unit.let {
        val _constructor = js("require('electron').Menu")
        js("new _constructor()")
    }, z = Unit)

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Properties ----------------------------------------------------------------------------

    /**
     * A MenuItem[] array containing the menu's items.
     *
     * Each Menu consists of multiple MenuItems and each MenuItem can have a submenu.
     */
    val items: Array<MenuItem> get() = (instance.items as Array<dynamic>).map { MenuItem(it, Unit) }.toTypedArray()


    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Pops up this menu as a context menu in the browserWindow.
     */
    fun popup(browserWindow: BrowserWindow?, x: Number?, y: Number, positioningItem: Number?): Unit = 
        instance.popup(browserWindow?.instance, x, y, positioningItem)

    /**
     * Appends the menuItem to the menu.
     */
    fun append(menuItem: MenuItem): Unit = 
        instance.append(menuItem.instance)

    /**
     * Inserts the menuItem to the pos position of the menu.
     */
    fun insert(pos: Int, menuItem: MenuItem): Unit = 
        instance.insert(pos, menuItem.instance)

    // ~ Companion -----------------------------------------------------------------------------

    companion object { 

        private val module: dynamic = js("require('electron').Menu")

        // ~ Methods -------------------------------------------------------------------------------

        /**
         * Sets menu as the application menu on macOS. On Windows and Linux, the menu 
         * will be set as each window's top menu.
         *
         * Note: This API has to be called after the ready event of app module.
         */
        fun setApplicationMenu(menu: Menu): Unit = 
            module.setApplicationMenu(menu.instance)

        /**
         */
        fun getApplicationMenu(): Menu = 
            module.getApplicationMenu()

        /**
         * Sends the action to the first responder of application. This is used for 
         * emulating default Cocoa menu behaviors, usually you would just use the role 
         * property of MenuItem.
         *
         * See the macOS Cocoa Event Handling Guide for more information on macOS' native 
         * actions.
         */
        fun sendActionToFirstResponder(action: String): Unit = 
            module.sendActionToFirstResponder(action)

        /**
         * Generally, the template is just an array of options for constructing a 
         * MenuItem. The usage can be referenced above.
         *
         * You can also attach other fields to the element of the template and they will 
         * become properties of the constructed menu items.
         */
        fun buildFromTemplate(template: Array<MenuItem.Options>): Menu = 
            module.buildFromTemplate(template)
    }

    // ~ Builders ------------------------------------------------------------------------------
}

