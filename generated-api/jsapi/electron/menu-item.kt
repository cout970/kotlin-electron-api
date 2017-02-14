@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

class MenuItem constructor(val instance: dynamic, z: Unit) {

    constructor(options: Options.() -> Unit) : this(Unit.let {
        val _constructor = js("require('electron').MenuItem")
        val _options = options.let { Options().apply(it) }
        js("new _constructor(_options)")
    }, z = Unit)

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Properties ----------------------------------------------------------------------------

    /**
     * A Boolean indicating whether the item is enabled, this property can be 
     * dynamically changed.
     */
    val enabled: Boolean get() = instance.enabled

    /**
     * A Boolean indicating whether the item is visible, this property can be 
     * dynamically changed.
     */
    val visible: Boolean get() = instance.visible

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
    val checked: Boolean get() = instance.checked

    /**
     * A String representing the menu items visible label
     */
    val label: String get() = instance.label

    /**
     * A Function that is fired when the MenuItem recieves a click event
     */
    val click: () -> Unit get() = instance.click



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
        var accelerator: String? = null,
        var icon: dynamic? = null,
        var enabled: Boolean? = null,
        var visible: Boolean? = null,
        var checked: Boolean? = null,
        var submenu: dynamic? = null,
        var id: String? = null,
        var position: String? = null
    )
}

