package jsapi.electron

class MenuItem(options: Options.() -> Unit) {

    val instance: dynamic

    init {
        val _constructor = js("require('electron').MenuItem")
        val _options = _options
        instance = js("new _constructor(/**
 * 
 */
/**
 * 
 */
/**
 * 
 */
    /**
     * Will be called with click(menuItem, browserWindow, event) when the menu item 
     * is clicked.
     */
    /**
     * Define the action of the menu item, when specified the click property will be 
     * ignored.
     */
    /**
     * Can be normal, separator, submenu, checkbox or radio.
     */
    /**
     * (optional)
     */
    /**
     * (optional)
     */
    /**
     * 
     */
    /**
     * 
     */
    /**
     * If false, the menu item will be greyed out and unclickable.
     */
    /**
     * If false, the menu item will be entirely hidden.
     */
    /**
     * Should only be specified for checkbox or radio type menu items.
     */
    /**
     * Should be specified for submenu type menu items. If submenu is specified, the 
     * type: 'submenu' can be omitted. If the value is not a Menu then it will be 
     * automatically converted to one using Menu.buildFromTemplate.
     */
    /**
     * Unique within a single menu. If defined then it can be used as a reference to 
     * this item by the position attribute.
     */
    /**
     * This field allows fine-grained definition of the specific location within a 
     * given menu.
     */
/**
 * 
 */
)")
    }

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Properties ----------------------------------------------------------------------------

    /**
     * A Boolean indicating whether the item is enabled, this property can be 
     * dynamically changed.
     */
    val enabled: dynamic get() = instance.enabled

    /**
     * A Boolean indicating whether the item is visible, this property can be 
     * dynamically changed.
     */
    val visible: dynamic get() = instance.visible

    /**
     * A Boolean indicating whether the item is checked, this property can be 
     * dynamically changed.
     *
     * A checkbox menu item will toggle the checked property on and off when selected.
     *
     * A radio menu item will turn on its checked property when clicked, and will 
     * turn off that property for all adjacent items in the same menu.
     *
     * You can add a click function for additional behavior.
     */
    val checked: dynamic get() = instance.checked

    /**
     * A String representing the menu items visible label
     */
    val label: dynamic get() = instance.label

    /**
     * A Function that is fired when the MenuItem recieves a click event
     */
    val click: dynamic get() = instance.click



    // ~ Companion -----------------------------------------------------------------------------

    companion object { 

        private val module: dynamic = js("require('electron').MenuItem")

    }

    // ~ Builders ------------------------------------------------------------------------------

    class Options(
        var click: ((menuItem: MenuItem, browserWindow: BrowserWindow, event: dynamic) -> Unit)? = null,
        var role: String? = null,
        var type: String? = null,
        var label: String? = null,
        var sublabel: String? = null,
        var accelerator: dynamic? = null,
        var icon: dynamic? = null,
        var enabled: Boolean? = null,
        var visible: Boolean? = null,
        var checked: Boolean? = null,
        var submenu: dynamic? = null,
        var id: String? = null,
        var position: String? = null
    )
}

